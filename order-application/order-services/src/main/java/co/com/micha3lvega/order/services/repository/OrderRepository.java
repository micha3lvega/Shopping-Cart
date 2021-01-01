package co.com.micha3lvega.order.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.order.services.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

}
