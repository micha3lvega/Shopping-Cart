package co.com.micha3lvega.shopping.cart.services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingCartApplicationConfig {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
}
