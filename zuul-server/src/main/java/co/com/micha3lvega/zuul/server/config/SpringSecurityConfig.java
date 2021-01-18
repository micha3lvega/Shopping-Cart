package co.com.micha3lvega.zuul.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import co.com.micha3lvega.zuul.server.filter.JWTAuthenticationFilter;
import co.com.micha3lvega.zuul.server.filter.JWTAuthorizationFilter;
import co.com.micha3lvega.zuul.server.services.JWTService;
import co.com.micha3lvega.zuul.server.services.UserAuthService;

/**
 * Clase encargada de la configuracion de los metodos de seguridad del proyecto
 * 
 * @author micha3lvega
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserAuthService userAuthService;

	@Autowired
	private Pbkdf2PasswordEncoder encoder;

	@Autowired
	private JWTService jwtService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		// Permitir el acceso a paginas
		.antMatchers("/oauth/**", "/product/**").permitAll()
		// Desabilitar el acceso a otras paginas
		.anyRequest().authenticated()
		// Desabilitat login form
		.and().formLogin().disable()
		// Disable CSRF
		.csrf().disable()
		// sesiones como staless necesario para jwt
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// Agregar filtro al momento de generar el token
		.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
		// Agregar filtro al momento de validar el token
		.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService));

	}

	/**
	 * Condfigurar la clase que se encarga de validar el usuario y el encoder de la
	 * contraseña
	 * 
	 * @param build
	 * @throws Exception
	 */
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userAuthService).passwordEncoder(encoder);
	}

}
