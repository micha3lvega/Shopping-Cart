package co.com.micha3lvega.zuul.server.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.micha3lvega.zuul.server.model.Token;
import co.com.micha3lvega.zuul.server.services.JWTService;

/**
 * Clase encargda de la configuracion de los metodos de un intento de
 * aunteticacion y la autenticacion exitosa
 * 
 * @author micha3lvega
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {

		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/oaut/token", "POST"));

		this.jwtService = jwtService;

	}

	/**
	 * Metodo que se ejecuta al momento de un intento de autenticacion
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authToken);

	}

	/**
	 * Metodo que se ejecuta cuando el usuario y contraseña son correctos
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		Token token = jwtService.create(authResult);

		Map<String, Object> body = new HashMap<>();
		body.put("token", token);

		response.setStatus(HttpStatus.OK.value());
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType("application/json");
	}

	/**
	 * Metodo que se ejecuta cuando el intento de autenticacion es invalido
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<>();
		body.put("message", "Usuario o contraseña invalido");
		body.put("message_EN", "Invalid username or password");

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setContentType("application/json");

	}

}
