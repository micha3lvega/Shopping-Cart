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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/unit")
@Api(tags = "Unidad de medida", description = "Operaciones para la administracion de las unidades de medida")
public class UnitOfMeasurementRestController {

	@Autowired
	private IUnitOfMeasurementServices services;

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos las unidades de medida que hay en el sistema", value = "Obtener todas las unidades de medida")
	public List<UnitOfMeasurementDTO> findAll() {
		return services.findAll();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Elimina una unidad de medida por su ID", produces = "application/json")
	public void delete(@PathVariable("id") String id) {
		services.delete(id);
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID de la unidades de medida es obligatorio", value = "Obtener una  unidades de medida por su ID")
	public UnitOfMeasurementDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea una unidad de medida", produces = "application/json")
	public UnitOfMeasurementDTO insert(@Valid @RequestBody UnitOfMeasurementDTO unitOfMeasurementDTO) {
		return services.insert(unitOfMeasurementDTO);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza una unidad de medida", produces = "application/json", notes = "El id de la unidad de medida es obligatorio")
	UnitOfMeasurementDTO update(@Valid @RequestBody UnitOfMeasurementDTO unitOfMeasurementDTO) {
		return services.update(unitOfMeasurementDTO);
	}

}
