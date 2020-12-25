package co.com.micha3lvega.product.services.services;

import java.util.List;

import co.com.micha3lvega.product.services.dto.BrandDTO;

public interface IBrandServices {

	List<BrandDTO> findAll();

	void delete(String id);

	BrandDTO findById(String id);

	BrandDTO insert(BrandDTO brand);

	BrandDTO update(BrandDTO brand);

}
