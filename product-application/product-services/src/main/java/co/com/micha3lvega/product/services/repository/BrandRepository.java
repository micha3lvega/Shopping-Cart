package co.com.micha3lvega.product.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.product.services.model.Brand;

public interface BrandRepository extends MongoRepository<Brand, String> {

	Brand findByName(String name);

}
