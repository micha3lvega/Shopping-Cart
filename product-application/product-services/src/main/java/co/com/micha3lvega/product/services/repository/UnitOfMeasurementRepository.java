package co.com.micha3lvega.product.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.product.services.model.UnitOfMeasurement;

public interface UnitOfMeasurementRepository extends MongoRepository<UnitOfMeasurement, String> {

	UnitOfMeasurement findByName(String name);

}
