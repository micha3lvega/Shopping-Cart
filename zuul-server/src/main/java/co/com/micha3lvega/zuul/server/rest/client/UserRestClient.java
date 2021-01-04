package co.com.micha3lvega.zuul.server.rest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.com.micha3lvega.product.commons.dto.UserDTO;

@FeignClient("USER-SERVICES")
public interface UserRestClient {

	@GetMapping("/api/v1/user/login/{email}/{password}")
	public UserDTO login(@PathVariable("email") String email, @PathVariable("password") String password);

	@GetMapping("/api/v1/user/email/{email}")
	public UserDTO findByEmail(@PathVariable("email") String email);

}
