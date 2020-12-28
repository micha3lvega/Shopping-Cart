package co.com.micha3lvega.country.services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.country.services.model.Country;

public interface CountryRepository extends MongoRepository<Country, String>{

}
