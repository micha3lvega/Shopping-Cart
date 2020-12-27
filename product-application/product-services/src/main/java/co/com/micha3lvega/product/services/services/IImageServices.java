package co.com.micha3lvega.product.services.services;

import org.springframework.web.multipart.MultipartFile;

import co.com.micha3lvega.product.services.dto.ImageDTO;

public interface IImageServices {

	ImageDTO create(String productID, MultipartFile file);

	ImageDTO update(String productID, MultipartFile file);

}
