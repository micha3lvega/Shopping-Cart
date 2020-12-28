package co.com.micha3lvega.country.services.services;

import java.util.List;

import co.com.micha3lvega.country.commons.dto.CountryDTO;

public interface ICountryServices {

	List<CountryDTO> findAll();

	CountryDTO findById(String id);

	CountryDTO create(CountryDTO country);
	
	CountryDTO update(CountryDTO country);
	
	List<CountryDTO> saveAll(List<CountryDTO> countries);

}
