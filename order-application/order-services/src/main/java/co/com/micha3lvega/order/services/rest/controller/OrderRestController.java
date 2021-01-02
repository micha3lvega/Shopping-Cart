package co.com.micha3lvega.order.services.rest.controller;

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

import co.com.micha3lvega.order.commons.dto.OrderDTO;
import co.com.micha3lvega.order.services.services.IOrderServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Orden")
@RequestMapping("/api/v1/order")
public class OrderRestController {

	@Autowired
	private IOrderServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos las ordenes que hay en el sistema", value = "Obtener todos las ordenes")
	public List<OrderDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID de la orden es obligatoria", value = "Obtener una orden por su ID")
	public OrderDTO findByID(@PathVariable("id") String id) {
		return services.findByID(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea una orden", produces = "application/json")
	public OrderDTO create(@Valid @RequestBody OrderDTO dto) {
		return services.create(dto);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza una orden", produces = "application/json", notes = "El id de la orden es obligatorio")
	public OrderDTO update(@Valid @RequestBody OrderDTO dto) {
		return services.update(dto);
	}

}
