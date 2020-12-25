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

import co.com.micha3lvega.product.services.dto.UnitOfMeasurementDTO;
import co.com.micha3lvega.product.services.services.IUnitOfMeasurementServices;

@RestController
@RequestMapping("/api/v1/unit")
public class UnitOfMeasurementRestController {

	@Autowired
	private IUnitOfMeasurementServices services;

	@GetMapping
	public List<UnitOfMeasurementDTO> findAll() {
		return services.findAll();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		services.delete(id);
	}

	@GetMapping("/{id}")
	public UnitOfMeasurementDTO findById(String id) {
		return services.findById(id);
	}

	@PostMapping
	public UnitOfMeasurementDTO insert(@Valid @RequestBody UnitOfMeasurementDTO unitOfMeasurementDTO) {
		return services.insert(unitOfMeasurementDTO);
	}

	@PutMapping
	UnitOfMeasurementDTO update(@Valid @RequestBody UnitOfMeasurementDTO unitOfMeasurementDTO) {
		return services.update(unitOfMeasurementDTO);
	}

}
