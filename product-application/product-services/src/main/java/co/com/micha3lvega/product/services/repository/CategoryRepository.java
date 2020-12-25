package co.com.micha3lvega.product.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.product.services.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

	Category findByName(String name);

}
