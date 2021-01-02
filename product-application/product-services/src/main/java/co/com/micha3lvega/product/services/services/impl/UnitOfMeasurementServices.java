package co.com.micha3lvega.product.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.micha3lvega.product.services.dto.UnitOfMeasurementDTO;
import co.com.micha3lvega.product.services.exception.unit.of.measurement.UnitOfMeasurementExistException;
import co.com.micha3lvega.product.services.exception.unit.of.measurement.UnitOfMeasurementNoExistException;
import co.com.micha3lvega.product.services.model.UnitOfMeasurement;
import co.com.micha3lvega.product.services.repository.UnitOfMeasurementRepository;
import co.com.micha3lvega.product.services.services.IUnitOfMeasurementServices;
import co.com.micha3lvega.product.services.util.Util;

@Service
public class UnitOfMeasurementServices implements IUnitOfMeasurementServices {

	@Autowired
	private UnitOfMeasurementRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<UnitOfMeasurementDTO> findAll() {
		return repository.findAll().stream()
				.map(unitOfMeasurement -> mapper.map(unitOfMeasurement, UnitOfMeasurementDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String id) {

		// Buscar que la entidad exista
		UnitOfMeasurement unitOfMeasurement = repository.findById(id)
				.orElseThrow(UnitOfMeasurementNoExistException::new);

		// Eliminar la unidad de medida
		repository.delete(unitOfMeasurement);

	}

	@Override
	public UnitOfMeasurementDTO findById(String id) {

		// Buscar que la entidad exista
		UnitOfMeasurement unitOfMeasurement = repository.findById(id)
				.orElseThrow(UnitOfMeasurementNoExistException::new);

		return mapper.map(unitOfMeasurement, UnitOfMeasurementDTO.class);
	}

	@Override
	public UnitOfMeasurementDTO insert(UnitOfMeasurementDTO unitOfMeasurementDTO) {

		// Normalizar el nombre
		if (unitOfMeasurementDTO != null && unitOfMeasurementDTO.getId() != null) {
			unitOfMeasurementDTO.setName(Util.capitalizeString(unitOfMeasurementDTO.getName()));
		}

		// Buscar que el nombre no se repita
		UnitOfMeasurement findUnitOfMeasurement = repository
				.findByName(unitOfMeasurementDTO != null ? unitOfMeasurementDTO.getName() : null);

		if (findUnitOfMeasurement != null) {
			throw new UnitOfMeasurementExistException();
		}

		// Guardar la unidad de medida en la base de datos
		UnitOfMeasurement newUnitOfMeasurement = repository
				.insert(mapper.map(unitOfMeasurementDTO, UnitOfMeasurement.class));

		return mapper.map(newUnitOfMeasurement, UnitOfMeasurementDTO.class);
	}

	@Override
	public UnitOfMeasurementDTO update(UnitOfMeasurementDTO unitOfMeasurementDTO) {

		// El id de la unidad de medida es obligatorio
		if (unitOfMeasurementDTO == null || unitOfMeasurementDTO.getId() == null) {
			throw new UnitOfMeasurementNoExistException();
		}

		// Buscar que el id si exista
		UnitOfMeasurement currentUnitOfMeasurement = repository.findById(unitOfMeasurementDTO.getId())
				.orElseThrow(UnitOfMeasurementNoExistException::new);

		// Normalizar el nombre
		if (unitOfMeasurementDTO.getName() != null) {
			unitOfMeasurementDTO.setName(Util.capitalizeString(unitOfMeasurementDTO.getName()));
		}

		// Buscar que el nombre no se repita
		UnitOfMeasurement findByNameUnitOfMeasurement = repository.findByName(unitOfMeasurementDTO.getName());

		/*
		 * Si los ids de la entidad recibida y la buscada por nombre son diferentes debe
		 * haber error por que esta tratando de ponerle el mismo nombre que una entidad
		 * que ya existe
		 */
		if (findByNameUnitOfMeasurement != null
				&& !currentUnitOfMeasurement.getId().equals(findByNameUnitOfMeasurement.getId())) {
			throw new UnitOfMeasurementExistException();
		}

		// Actualizar la entidad
		UnitOfMeasurement updateUnitOfMeasurement = repository
				.save(mapper.map(unitOfMeasurementDTO, UnitOfMeasurement.class));

		return mapper.map(updateUnitOfMeasurement, UnitOfMeasurementDTO.class);
	}

}
