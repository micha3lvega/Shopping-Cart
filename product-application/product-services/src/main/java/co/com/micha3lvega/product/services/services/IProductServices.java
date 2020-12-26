package co.com.micha3lvega.product.services.services;

import java.util.List;

import co.com.micha3lvega.product.services.dto.ProductDTO;

public interface IProductServices {

	List<ProductDTO> findAll();

	ProductDTO findById(String id);

	ProductDTO create(ProductDTO dto);

	ProductDTO update(ProductDTO dto);

}
