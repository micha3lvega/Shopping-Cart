package co.com.micha3lvega.user.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.user.services.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);

}
