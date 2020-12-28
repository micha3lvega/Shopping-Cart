package co.com.micha3lvega.country.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.micha3lvega.country.services.model.Country;

public interface CountryRepository extends MongoRepository<Country, ID>{

}
