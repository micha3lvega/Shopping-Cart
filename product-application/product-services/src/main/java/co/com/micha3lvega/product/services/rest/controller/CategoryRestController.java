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

@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

	@Autowired
	private ICategoryServices services;

	@GetMapping
	public List<CategoryDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	public CategoryDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PostMapping
	public CategoryDTO insert(@Valid @RequestBody CategoryDTO category) {
		return services.insert(category);
	}

	@PutMapping
	public CategoryDTO update(@Valid @RequestBody CategoryDTO category) {
		return services.update(category);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		services.delete(id);
	}
}
