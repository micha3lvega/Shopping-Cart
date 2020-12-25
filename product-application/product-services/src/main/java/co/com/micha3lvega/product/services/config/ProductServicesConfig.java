package co.com.micha3lvega.product.services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServicesConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
