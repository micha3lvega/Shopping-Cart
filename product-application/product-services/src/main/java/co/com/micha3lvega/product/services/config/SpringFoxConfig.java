package co.com.micha3lvega.product.services.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("co.com.micha3lvega.product.services.rest.controller"))
				.paths(PathSelectors.any()).build().apiInfo(this.apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("API rest servicios de productos", "Api para el manejo de los servicios de productos.",
				"API TOS", "Terms of service", new Contact("Michael Vega", "www.example.com", "micha3lvega@gmail."),
				"License of API", "API license URL", Collections.emptyList());
	}

}
