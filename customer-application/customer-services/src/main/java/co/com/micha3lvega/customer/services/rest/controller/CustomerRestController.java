package co.com.micha3lvega.customer.services.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.customer.services.services.ICustomerServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Clientes")
@RequestMapping("/api/v1/customer")
public class CustomerRestController {

	@Autowired
	private ICustomerServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos los clientes que hay en el sistema", value = "Obtener todos los clientes")
	public List<CustomerDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del cliente es obligatorio", value = "Obtener un cliente por su ID")
	public CustomerDTO findByID(@PathVariable("id") String id) {
		return services.findByID(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea un cliente", produces = "application/json")
	public CustomerDTO create(@Valid @RequestBody CustomerDTO dto) {
		return services.create(dto);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza un cliente", produces = "application/json", notes = "El id del cliente es obligatorio")
	public CustomerDTO update(@Valid @RequestBody CustomerDTO dto) {
		return services.update(dto);
	}
}
