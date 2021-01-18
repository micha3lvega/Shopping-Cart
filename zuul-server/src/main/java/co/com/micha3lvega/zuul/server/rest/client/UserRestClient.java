package co.com.micha3lvega.zuul.server.rest.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.micha3lvega.product.commons.dto.UserDTO;

@FeignClient("USER-SERVICES")
public interface UserRestClient {

	@GetMapping("/api/v1/user")
	public List<UserDTO> findAll();

	@GetMapping("/api/v1/user/{id}")
	public UserDTO findById(@PathVariable("id") String id);

	@GetMapping("/api/v1/user/email/{email}")
	public UserDTO findByEmail(@PathVariable("email") String email);

	@PutMapping("/api/v1/user")
	public UserDTO update(@Valid @RequestBody UserDTO dto);

}
