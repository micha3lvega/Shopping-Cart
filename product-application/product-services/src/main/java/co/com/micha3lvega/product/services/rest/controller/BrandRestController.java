package co.com.micha3lvega.product.services.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.micha3lvega.product.services.dto.BrandDTO;
import co.com.micha3lvega.product.services.services.IBrandServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Marcas")
@RequestMapping("/api/v1/brand")
public class BrandRestController {

	@Autowired
	private IBrandServices brandServices;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos las marcas que hay en el sistema", value = "Obtener todas las marcas")
	public List<BrandDTO> findAll() {
		return brandServices.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID de la marca es obligatorio", value = "Obtener una marca por su ID")
	public BrandDTO findById(@PathVariable("id") String id) {
		return brandServices.findById(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea una marca", produces = "application/json")
	public BrandDTO insert(@Valid @RequestBody BrandDTO brand) {
		return brandServices.insert(brand);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza una marca", produces = "application/json", notes = "El id de la marca es obligatorio")
	public BrandDTO update(@Valid @RequestBody BrandDTO brand) {
		return brandServices.update(brand);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina una marca por su ID", produces = "application/json")
	public void delete(@PathVariable("id") String id) {
		brandServices.delete(id);
	}

}
