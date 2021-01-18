package co.com.micha3lvega.zuul.server.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import co.com.micha3lvega.product.commons.dto.RolDTO;
import co.com.micha3lvega.zuul.server.model.Token;
import co.com.micha3lvega.zuul.server.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {

	@Override
	public Token create(Authentication auth) throws IOException {

		// Propieadades iniciales del token
		Date createDate = new Date();

		String username = ((User) auth.getPrincipal()).getUsername();

		Token token = new Token();

		Date expirateDate = new Date(System.currentTimeMillis() + Token.EXPIRATION_DATE_ACCESS_TOKEN);
		Date expirateDateRefreshToken = new Date(System.currentTimeMillis() + Token.EXPIRATION_REFRESH_ACCESS_TOKEN);

		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		boolean isAdmin = roles.contains(new SimpleGrantedAuthority(RolDTO.ADMIN.name()));

		Claims claims = Jwts.claims();
		claims.put("authorities", roles);
		if (isAdmin) {
			claims.put("is_admin", isAdmin);
			token.setIsAdmin(isAdmin);
		}

		String accessToken = Jwts.builder().setClaims(claims).setSubject(username)
				.signWith(SignatureAlgorithm.HS512, Token.SECRET.getBytes()).setIssuedAt(createDate)
				.setExpiration(expirateDate).compact();

		String refreshToken = Jwts.builder().setClaims(claims).setSubject(username)
				.signWith(SignatureAlgorithm.HS512, Token.SECRET_REFRESH.getBytes()).setIssuedAt(createDate)
				.setExpiration(expirateDateRefreshToken).compact();

		token.setCreateDate(createDate);
		token.setAccessToken(accessToken);
		token.setExpiresIn(createDate.getTime() + Token.EXPIRATION_DATE_ACCESS_TOKEN);

		token.setRefreshToken(refreshToken);
		token.setRefreshTokenExpiresIn(createDate.getTime() + Token.EXPIRATION_REFRESH_ACCESS_TOKEN);

		return token;

	}

	@Override
	public boolean validate(String token) {

		try {

			getClaims(token);
			return true;

		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(Token.SECRET.getBytes()).parseClaimsJws(resolve(token)).getBody();
	}

	@Override
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<GrantedAuthority> getRoles(String token) throws IOException {

		List<GrantedAuthority> authorities = new ArrayList<>();

		ArrayList<LinkedHashMap<String, String>> roles = (ArrayList<LinkedHashMap<String, String>>) getClaims(token)
				.get("authorities");

		roles.forEach(rol -> {
			rol.forEach((key, value) -> {
				authorities.add(new SimpleGrantedAuthority(value));
				System.out.println("key: " + key + ", value: " + value);
			});
		});

		return authorities;

	}

	@Override
	public String resolve(String token) {
		if (token != null && token.startsWith(Token.TOKEN_TYPE)) {
			return token.replace(Token.TOKEN_TYPE, "").strip();
		}
		return null;
	}

}
