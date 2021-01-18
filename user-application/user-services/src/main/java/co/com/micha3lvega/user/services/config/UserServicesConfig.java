package co.com.micha3lvega.user.services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import co.com.micha3lvega.product.commons.dto.SecurityDTO;

@Configuration
public class UserServicesConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	public SecurityDTO initSecurityDTO() {

		SecurityDTO security = new SecurityDTO();

		security.setEnabled(true);
		security.setAccessToken(null);
		security.setAccountNonLocked(true);
		security.setAccountNonExpired(true);
		security.setCredentialsNonExpired(true);

		return security;

	}

	@Bean
	public Pbkdf2PasswordEncoder encoder() {
		return new Pbkdf2PasswordEncoder("co.com.micha3lvega.user.services.config.secret", 10000, 128);
	}
}
