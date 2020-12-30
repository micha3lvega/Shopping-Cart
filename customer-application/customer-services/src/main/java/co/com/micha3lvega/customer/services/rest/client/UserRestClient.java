package co.com.micha3lvega.customer.services.rest.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.micha3lvega.product.commons.dto.UserDTO;

@FeignClient("USER-SERVICES")
public interface UserRestClient {
	
	@GetMapping("/api/v1/user")
	public List<UserDTO> findAll();

	@GetMapping("/api/v1/user/{id}")
	public UserDTO findById(@PathVariable("id") String id);
	
	@PostMapping("/api/v1/user")
	public UserDTO create(@Valid @RequestBody UserDTO dto) ;
	
	@PutMapping("/api/v1/user")
	public UserDTO update(@Valid @RequestBody UserDTO dto);
	
	@PatchMapping("/api/v1/user/changepassword/{id}/{newPassword}")
	public UserDTO updatePassword(@PathVariable("id") String id, @PathVariable("newPassword") String newPassword);

	@GetMapping("/api/v1/user/login/{email}/{password}")
	public UserDTO login(@PathVariable("email") String email, @PathVariable("password") String password);
	
}
