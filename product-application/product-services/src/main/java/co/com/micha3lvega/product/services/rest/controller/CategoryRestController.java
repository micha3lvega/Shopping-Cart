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

import co.com.micha3lvega.product.services.dto.CategoryDTO;
import co.com.micha3lvega.product.services.services.ICategoryServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Categorias")
@RequestMapping("/api/v1/category")
public class CategoryRestController {

	@Autowired
	private ICategoryServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos las categorias que hay en el sistema", value = "Obtener todas las categorias")
	public List<CategoryDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID de la categoria es obligatorio", value = "Obtener una categoria por su ID")
	public CategoryDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea una categoria", produces = "application/json")
	public CategoryDTO insert(@Valid @RequestBody CategoryDTO category) {
		return services.insert(category);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza una categoria", produces = "application/json", notes = "El id de la categoria es obligatorio")
	public CategoryDTO update(@Valid @RequestBody CategoryDTO category) {
		return services.update(category);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina una categoria por su ID", produces = "application/json")
	public void delete(@PathVariable("id") String id) {
		services.delete(id);
	}
}
