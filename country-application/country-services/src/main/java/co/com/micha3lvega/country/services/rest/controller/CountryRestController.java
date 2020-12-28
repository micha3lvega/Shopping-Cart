package co.com.micha3lvega.country.services.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import co.com.micha3lvega.country.services.services.ICountryServices;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/country")
@Api(tags = "Marcas", description = "Operaciones para la administracion de los paises")
public class CountryRestController {

	@Autowired
	private ICountryServices services;

	@GetMapping
	public List<CountryDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	public CountryDTO findById(@PathVariable("id") String id) {
		return services.findById(id);
	}

	@PostMapping
	public CountryDTO create(@Valid @RequestBody CountryDTO country) {
		return services.create(country);
	}
	
	@PutMapping
	public CountryDTO update(@Valid @RequestBody CountryDTO country) {
		return services.update(country);
	}

	@PatchMapping
	public List<CountryDTO> saveAll(@Valid @RequestBody List<CountryDTO> countries){
		return services.saveAll(countries);
	}
}
