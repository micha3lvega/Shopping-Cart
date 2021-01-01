package co.com.micha3lvega.order.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
