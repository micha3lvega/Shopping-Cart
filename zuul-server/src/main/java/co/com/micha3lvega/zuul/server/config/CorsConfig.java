package co.com.micha3lvega.zuul.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class CorsConfig {

	/**
	 * Configuracion de los cors para los diferentes PATHS
	 * Aqui se configura para el acceso desde clientes diferentes a los navegadores
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/product/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

}
