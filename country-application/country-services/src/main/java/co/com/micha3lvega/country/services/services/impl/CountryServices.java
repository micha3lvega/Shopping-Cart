/**
 * 
 */
package co.com.micha3lvega.country.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import co.com.micha3lvega.country.services.exception.country.CountryNotExistsExcepction;
import co.com.micha3lvega.country.services.model.Country;
import co.com.micha3lvega.country.services.repository.CountryRepository;
import co.com.micha3lvega.country.services.services.ICountryServices;

@Service
public class CountryServices implements ICountryServices {

	@Autowired
	private CountryRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<CountryDTO> findAll() {
		return repository.findAll().stream().map(country -> mapper.map(country, CountryDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CountryDTO findById(String id) {

		// validar que el objecto tenga un id
		if (id == null || id.isEmpty()) {
			throw new CountryNotExistsExcepction();
		}

		// Buscar el pais si no existe retornar una excepcion
		Country country = repository.findById(id).orElseThrow(CountryNotExistsExcepction::new);
		return mapper.map(country, CountryDTO.class);

	}

	@Override
	@Transactional
	public CountryDTO create(CountryDTO country) {

		// crear el pais
		Country newCountry = repository.insert(mapper.map(country, Country.class));
		return mapper.map(newCountry, CountryDTO.class);

	}

	@Override
	@Transactional
	public CountryDTO update(CountryDTO country) {

		// validar que el objecto tenga un id
		if (country == null || country.getId().isEmpty()) {
			throw new CountryNotExistsExcepction();
		}

		// Buscar el pais si no existe retornar una excepcion
		if (!repository.findById(country.getId()).isPresent()) {
			throw new CountryNotExistsExcepction();
		}

		// actualizar el pais
		Country updateCountry = repository.save(mapper.map(country, Country.class));
		return mapper.map(updateCountry, CountryDTO.class);

	}

	@Override
	@Transactional
	public List<CountryDTO> saveAll(List<CountryDTO> countries) {

		// Convertir a un arreglo de entidades
		List<Country> newCountries = countries.stream().map(country -> mapper.map(country, Country.class))
				.collect(Collectors.toList());

		// Guardar los paises
		newCountries = repository.saveAll(newCountries);

		// Convertir a un arreglo de DTO's y retornarlos
		return newCountries.stream().map(country -> mapper.map(country, CountryDTO.class)).collect(Collectors.toList());

	}

}
