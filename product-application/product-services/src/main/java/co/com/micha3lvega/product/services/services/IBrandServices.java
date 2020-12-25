package co.com.micha3lvega.product.services.services;

import java.util.List;

import co.com.micha3lvega.product.services.dto.BrandDTO;
import co.com.micha3lvega.product.services.exception.brand.BrandExistException;
import co.com.micha3lvega.product.services.exception.brand.BrandNoExistException;

public interface IBrandServices {

	List<BrandDTO> findAll();

	void delete(String id) throws BrandNoExistException;

	BrandDTO findById(String id) throws BrandNoExistException;

	BrandDTO insert(BrandDTO brand) throws BrandExistException;

	BrandDTO update(BrandDTO brand) throws BrandNoExistException;

}
