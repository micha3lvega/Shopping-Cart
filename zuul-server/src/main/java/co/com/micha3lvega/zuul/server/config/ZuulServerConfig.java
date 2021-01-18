package co.com.micha3lvega.zuul.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Clase encargada de la configuracion del proyecto
 * @author micha3lvega
 *
 */
@Configuration
public class ZuulServerConfig {

	/**
	 * Configuracion del encoder de la contraseña
	 * @return
	 */
	@Bean
	public Pbkdf2PasswordEncoder encoder() {
		return new Pbkdf2PasswordEncoder("co.com.micha3lvega.user.services.config.secret", 10000, 128);
	}

}
