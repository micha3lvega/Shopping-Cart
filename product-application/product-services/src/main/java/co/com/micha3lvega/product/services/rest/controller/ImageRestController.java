package co.com.micha3lvega.product.services.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.com.micha3lvega.product.services.dto.ImageDTO;
import co.com.micha3lvega.product.services.services.IImageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Imagenes", description = "Operaciones para la administracion de las imagenes del producto")
@RequestMapping("/api/v1/image")
public class ImageRestController {

	@Autowired
	private IImageServices services;

	@PostMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "Agregar la imagen del producto", value = "Agregar la imagen del producto")
	public ImageDTO create(@PathVariable("id") String productID, @RequestParam("file") MultipartFile file) {
		return services.create(productID, file);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "Actualiza la imagen del producto", value = "Actualiza la imagen del producto")
	public ImageDTO update(@PathVariable("id") String productID, @RequestParam("file") MultipartFile file) {
		return services.update(productID, file);
	}

}
