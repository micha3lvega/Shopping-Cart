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

@RestController
@RequestMapping("/api/v1/brand")
public class BrandRestController {

	@Autowired
	private IBrandServices brandServices;

	@GetMapping
	public List<BrandDTO> findAll() {
		return brandServices.findAll();
	}

	@GetMapping("/{id}")
	public BrandDTO findById(@PathVariable("id") String id) {
		return brandServices.findById(id);
	}

	@PostMapping
	public BrandDTO insert(@Valid @RequestBody BrandDTO brand) {
		return brandServices.insert(brand);
	}

	@PutMapping
	public BrandDTO update(@Valid @RequestBody BrandDTO brand) {
		return brandServices.update(brand);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		brandServices.delete(id);
	}

}
