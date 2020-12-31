package co.com.micha3lvega.customer.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.customer.services.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer,String>{

}
