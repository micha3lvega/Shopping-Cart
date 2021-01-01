package co.com.micha3lvega.shopping.cart.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingCartServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartServicesApplication.class, args);
	}

}
