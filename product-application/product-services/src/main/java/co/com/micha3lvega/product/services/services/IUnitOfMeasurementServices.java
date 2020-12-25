package co.com.micha3lvega.product.services.services;

import java.util.List;

import co.com.micha3lvega.product.services.dto.UnitOfMeasurementDTO;

public interface IUnitOfMeasurementServices {

	List<UnitOfMeasurementDTO> findAll();

	void delete(String id);

	UnitOfMeasurementDTO findById(String id);

	UnitOfMeasurementDTO insert(UnitOfMeasurementDTO unitOfMeasurementDTO);

	UnitOfMeasurementDTO update(UnitOfMeasurementDTO unitOfMeasurementDTO);

}
