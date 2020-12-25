package co.com.micha3lvega.product.services.services;

import java.util.List;

import co.com.micha3lvega.product.services.dto.CategoryDTO;

public interface ICategoryServices {

	List<CategoryDTO> findAll();

	void delete(String id);

	CategoryDTO findById(String id);

	CategoryDTO insert(CategoryDTO category);

	CategoryDTO update(CategoryDTO category);

}
