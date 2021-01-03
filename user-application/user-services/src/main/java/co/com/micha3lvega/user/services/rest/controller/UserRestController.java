package co.com.micha3lvega.user.services.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import co.com.micha3lvega.user.services.services.IUserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Usuarios")
@RequestMapping("/api/v1/user")
public class UserRestController {

	@Autowired
	private IUserServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos los usuarios que hay en el sistema", value = "Obtener todos los usuarios")
	public List<UserDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del usuario es obligatorio", value = "Obtener un usuario por su ID")
	public UserDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PutMapping("/enable/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del usuario es obligatorio", value = "Activa un usuario por su ID")
	public UserDTO enable(@PathVariable("id") String id) {
		return services.enable(id);
	}

	@PutMapping("/disable/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del usuario es obligatorio", value = "Desactiva un usuario por su ID")
	public UserDTO disable(@PathVariable("id") String id) {
		return services.disable(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea un usuario", produces = "application/json")
	public UserDTO create(@Valid @RequestBody UserDTO dto) {
		return services.create(dto);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza un usuario por su id", produces = "application/json", notes = "El ID del usuario es obligatorio")
	public UserDTO update(@Valid @RequestBody UserDTO dto) {
		return services.update(dto);
	}

	@PatchMapping("/changepassword/{id}/{newPassword}")
	@ApiOperation(value = "Actualiza la contraseña de un usuario por su id", produces = "application/json", notes = "El ID del usuario es obligatorio")
	public UserDTO updatePassword(@PathVariable("id") String id, @PathVariable("newPassword") String newPassword) {
		return services.updatePassword(id, newPassword);
	}

	@GetMapping("/login/{email}/{password}")
	@ApiOperation(value = "Valida la autenticacion de un usuario", produces = "application/json")
	public UserDTO login(@PathVariable("email") String email, @PathVariable("password") String password) {
		return services.login(email, password);
	}

}
