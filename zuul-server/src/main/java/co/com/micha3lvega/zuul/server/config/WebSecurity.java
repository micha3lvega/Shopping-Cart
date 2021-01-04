package co.com.micha3lvega.zuul.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import co.com.micha3lvega.zuul.server.filter.JWTAuthenticationFilter;
import co.com.micha3lvega.zuul.server.filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	@Autowired
	private Pbkdf2PasswordEncoder encoder;

	public WebSecurity(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
				// Se desactiva el uso de cookies
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// Se activa la configuración CORS con los valores por defecto
				.cors().and()
				// Se desactiva el filtro CSRF
				.csrf().disable()
				// paths sin autenticacion
				.authorizeRequests().antMatchers("/user/**").permitAll().and()
				// path login
				.authorizeRequests().antMatchers("/oauth/**").permitAll().and()
				// path login
				.authorizeRequests().antMatchers("/login/**").permitAll()
				// Se indica que el resto de URLs esten securizadas
				.anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
		;

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las
		// passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
