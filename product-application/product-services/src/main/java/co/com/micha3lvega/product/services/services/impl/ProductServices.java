package co.com.micha3lvega.product.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.product.services.dto.ProductDTO;
import co.com.micha3lvega.product.services.exception.product.ProductExistException;
import co.com.micha3lvega.product.services.exception.product.ProductNoExistException;
import co.com.micha3lvega.product.services.model.Product;
import co.com.micha3lvega.product.services.repository.ProductRepository;
import co.com.micha3lvega.product.services.services.IProductServices;
import co.com.micha3lvega.product.services.util.Util;

@Service
public class ProductServices implements IProductServices {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		return repository.findAll().stream().map(product -> mapper.map(product, ProductDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDTO findById(String id) {

		// Buscar la entidad si no existe retornar una excepcion
		Product product = repository.findById(id).orElseThrow(ProductNoExistException::new);
		return mapper.map(product, ProductDTO.class);

	}

	@Override
	@Transactional
	public ProductDTO create(ProductDTO dto) {

		// Normalizar el nombre
		dto.setName(Util.capitalizeString(dto.getName()));

		// Buscar si existe un producto con este nombre
		Product findProduct = repository.findByName(dto.getName());

		if (findProduct != null) {
			throw new ProductExistException();
		}

		Product product = mapper.map(dto, Product.class);
		product = repository.insert(product);

		return mapper.map(product, ProductDTO.class);

	}

	@Override
	@Transactional
	public ProductDTO update(ProductDTO dto) {

		// Normalizar el nombre
		dto.setName(Util.capitalizeString(dto.getName()));

		// Buscar que exista la entidad a actualizar
		Product oldProduct = repository.findById(dto.getId()).orElseThrow(ProductNoExistException::new);

		// Buscar si existe un producto con este nombre
		Product findProductName = repository.findByName(dto.getName());

		/*
		 * Si los ids de la entidad recibida y la buscada por nombre son diferentes debe
		 * haber error por que esta tratando de ponerle el mismo nombre que una entidad
		 * que ya existe
		 */
		if (findProductName != null && !oldProduct.getId().equals(findProductName.getId())) {
			throw new ProductExistException();
		}

		// Actualizar la entidad
		Product updateProduct = repository.save(mapper.map(dto, Product.class));

		return mapper.map(updateProduct, ProductDTO.class);
	}

}
