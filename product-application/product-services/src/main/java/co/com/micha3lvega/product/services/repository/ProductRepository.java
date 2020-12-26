package co.com.micha3lvega.product.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.product.services.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	Product findByName(String name);

}
