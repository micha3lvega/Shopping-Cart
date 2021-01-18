package co.com.micha3lvega.zuul.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import co.com.micha3lvega.zuul.server.model.Token;
import co.com.micha3lvega.zuul.server.services.JWTService;

/**
 * Clase que se encarga de la autorizacion cuando se intenta consultar un
 * recurso protegido
 * 
 * @author micha3lvega
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
	}

	/**
	 * Filtro que se activa al intentar autenticarce al enviar el token
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(Token.HEADER_STRING);
		System.out.println("Authorization: " + header);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = null;

		if (jwtService.validate(header)) {
			authentication = new UsernamePasswordAuthenticationToken(jwtService.getUsername(header), null,
					jwtService.getRoles(header));
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	/**
	 * Metodo que valida si se esta recibiendo el token
	 * 
	 * @param header
	 * @return
	 */
	protected boolean requiresAuthentication(String header) {

		return header == null || header.startsWith(Token.TOKEN_TYPE);

	}

}
