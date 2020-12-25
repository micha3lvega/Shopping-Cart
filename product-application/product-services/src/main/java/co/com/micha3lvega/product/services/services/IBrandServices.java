package co.com.micha3lvega.product.services.services;

import co.com.micha3lvega.product.services.dto.BrandDTO;
import co.com.micha3lvega.product.services.exception.BrandExistException;
import co.com.micha3lvega.product.services.exception.BrandNoExistException;

public interface IBrandServices {

	BrandDTO findById(String id) throws BrandNoExistException;

	BrandDTO insert(BrandDTO brand) throws BrandExistException;

	BrandDTO update(BrandDTO brand) throws BrandNoExistException;
	
}
