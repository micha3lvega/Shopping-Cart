package co.com.micha3lvega.product.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.product.services.dto.BrandDTO;
import co.com.micha3lvega.product.services.exception.brand.BrandExistException;
import co.com.micha3lvega.product.services.exception.brand.BrandNoExistException;
import co.com.micha3lvega.product.services.model.Brand;
import co.com.micha3lvega.product.services.repository.BrandRepository;
import co.com.micha3lvega.product.services.services.IBrandServices;
import co.com.micha3lvega.product.services.util.Util;

@Service
public class BrandServices implements IBrandServices {

	@Autowired
	private BrandRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<BrandDTO> findAll() {
		return repository.findAll().stream().map(brand -> mapper.map(brand, BrandDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public BrandDTO findById(String id) {

		Brand brand = repository.findById(id).orElseThrow(() -> new BrandNoExistException());
		return mapper.map(brand, BrandDTO.class);
	}

	@Override
	@Transactional
	public BrandDTO insert(BrandDTO brand) {

		// Normalizar nombre
		if (brand != null && brand.getName() != null) {
			brand.setName(Util.capitalizeString(brand.getName()));
		}

		// Buscar que el nombre no se repita
		Brand findBrand = repository.findByName(brand != null ? brand.getName() : null);

		if (findBrand != null) {
			throw new BrandExistException();
		}

		Brand obj = repository.insert(mapper.map(brand, Brand.class));
		return mapper.map(obj, BrandDTO.class);
	}

	@Override
	@Transactional
	public BrandDTO update(BrandDTO brand) {

		if (brand == null || brand.getId() == null) {
			throw new BrandNoExistException();
		}

		// Validar que la marca exista
		Brand oldBrand = repository.findById(brand.getId()).orElse(null);
		if (oldBrand == null) {
			throw new BrandNoExistException();
		}

		// Normalizar nombre
		if (brand.getName() != null) {
			brand.setName(Util.capitalizeString(brand.getName()));
		}

		// Buscar que el nombre no se repita
		Brand findBrand = repository.findByName(brand.getName());
		if (findBrand != null && !oldBrand.getId().equals(findBrand.getId())) {
			throw new BrandExistException();
		}

		Brand obj = repository.save(mapper.map(brand, Brand.class));
		return mapper.map(obj, BrandDTO.class);

	}

	@Override
	@Transactional
	public void delete(String id) {

		// Buscar que exista la entidad
		Brand brand = repository.findById(id).orElseThrow(() -> new BrandNoExistException());
		repository.delete(brand);

	}

}
