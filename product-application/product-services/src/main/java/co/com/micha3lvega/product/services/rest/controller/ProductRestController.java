package co.com.micha3lvega.product.services.rest.controller;

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

import co.com.micha3lvega.product.services.dto.ProductDTO;
import co.com.micha3lvega.product.services.services.IProductServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Productos")
@RequestMapping("/api/v1/product")
public class ProductRestController {

	@Autowired
	private IProductServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos los productos que hay en el sistema", value = "Obtener todos los productos")
	public List<ProductDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del producto es obligatorio", value = "Obtener un producto por su ID")
	public ProductDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea un producto", produces = "application/json")
	public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
		return services.create(dto);
	}
	
	@PutMapping
	@ApiOperation(value = "Actualiza un producto", produces = "application/json", notes = "El id del producto es obligatorio")
	public ProductDTO update(@Valid @RequestBody ProductDTO dto) {
		return services.update(dto);
	}

}
