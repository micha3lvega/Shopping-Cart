package co.com.micha3lvega.customer.services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerApplicationConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
