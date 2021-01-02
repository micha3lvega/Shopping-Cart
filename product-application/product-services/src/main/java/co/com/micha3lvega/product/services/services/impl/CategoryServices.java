package co.com.micha3lvega.product.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.product.services.dto.CategoryDTO;
import co.com.micha3lvega.product.services.exception.category.CategoryExistException;
import co.com.micha3lvega.product.services.exception.category.CategoryNoExistException;
import co.com.micha3lvega.product.services.model.Category;
import co.com.micha3lvega.product.services.repository.CategoryRepository;
import co.com.micha3lvega.product.services.services.ICategoryServices;
import co.com.micha3lvega.product.services.util.Util;

@Service
public class CategoryServices implements ICategoryServices {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		return repository.findAll().stream().map(category -> mapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void delete(String id) {

		// Buscar que exista la entidad
		Category category = repository.findById(id).orElseThrow(CategoryNoExistException::new);
		repository.delete(category);

	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findById(String id) {

		// Buscar que exista la entidad
		Category category = repository.findById(id).orElseThrow(CategoryNoExistException::new);
		return mapper.map(category, CategoryDTO.class);

	}

	@Override
	@Transactional
	public CategoryDTO insert(CategoryDTO category) {

		// Normalizar nombre
		if (category != null && category.getName() != null) {
			category.setName(Util.capitalizeString(category.getName()));
		}

		// Buscar que el nombre no se repita
		Category findCategory = repository.findByName(category != null ? category.getName() : null);
		if (findCategory != null) {
			throw new CategoryExistException();
		}

		// Guardar categoria en base de datos
		Category newCategory = repository.insert(mapper.map(category, Category.class));

		return mapper.map(newCategory, CategoryDTO.class);
	}

	@Override
	@Transactional
	public CategoryDTO update(CategoryDTO category) {

		if (category == null || category.getId() == null) {
			throw new CategoryNoExistException();
		}

		// Buscar que el id exista
		Category currentCategory = repository.findById(category.getId()).orElseThrow(CategoryNoExistException::new);

		// Normalizar nombre
		if (category.getName() != null) {
			category.setName(Util.capitalizeString(category.getName()));
		}

		// Buscar que el nombre no se repita
		Category findCategory = repository.findByName(category.getName());
		if (findCategory != null && !currentCategory.getId().equals(findCategory.getId())) {
			throw new CategoryExistException();
		}

		// Actualizar
		Category updateCategory = repository.save(mapper.map(category, Category.class));

		return mapper.map(updateCategory, CategoryDTO.class);
	}

}
