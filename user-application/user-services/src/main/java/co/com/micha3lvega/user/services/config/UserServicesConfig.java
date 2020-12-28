package co.com.micha3lvega.user.services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class UserServicesConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public Pbkdf2PasswordEncoder encoder() {
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder("co.com.micha3lvega.user.services.config.secret",
				10000, 128);
		return encoder;
	}
}
