package co.com.micha3lvega.product.services.services.impl;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.com.micha3lvega.product.services.dto.ImageDTO;
import co.com.micha3lvega.product.services.exception.image.ImageIOException;
import co.com.micha3lvega.product.services.exception.image.NoImageException;
import co.com.micha3lvega.product.services.exception.product.ProductNoExistException;
import co.com.micha3lvega.product.services.model.Image;
import co.com.micha3lvega.product.services.model.Product;
import co.com.micha3lvega.product.services.repository.ProductRepository;
import co.com.micha3lvega.product.services.services.IImageServices;

@Service
public class ImageServices implements IImageServices {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public ImageDTO create(String productID, MultipartFile file) {

		// Validar que venga una imagen
		if (file == null) {
			throw new NoImageException();
		}

		// Buscar el producto
		Product product = repository.findById(productID).orElseThrow(ProductNoExistException::new);

		try {

			// Armar imagen
			Image image = new Image();
			image.setContentType(file.getContentType());
			image.setData(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
			image.setName(productID + "-" + file.getOriginalFilename());
			image.setSize(file.getSize());

			product.setImage(image);

			// Actualizar producto
			repository.save(product);

			return mapper.map(image, ImageDTO.class);

		} catch (IOException e) {
			throw new ImageIOException();
		}

	}

	@Override
	@Transactional
	public ImageDTO update(String productID, MultipartFile file) {
		return create(productID, file);
	}

}
