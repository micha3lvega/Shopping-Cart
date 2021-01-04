package co.com.micha3lvega.zuul.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class ZuulServerConfig {

	@Bean
	public Pbkdf2PasswordEncoder encoder() {
		return new Pbkdf2PasswordEncoder("co.com.micha3lvega.user.services.config.secret", 10000, 128);
	}

}
