package co.com.micha3lvega.shopping.cart.services.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.micha3lvega.shopping.cart.commons.dto.ShoppingCartDTO;
import co.com.micha3lvega.shopping.cart.services.services.IShoppingCartServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/shopping/cart")
@Api(tags = "Carrito de compras", description = "Operaciones para la administracion de los carritos de compras")
public class ShoppingCartRestController {

	@Autowired
	private IShoppingCartServices services;

	@DeleteMapping("/{id}")
	@ApiOperation(notes = "El ID del carrito de compras es obligatorio", value = "Elimina un carrito de compras por su ID")
	public void delete(String id) {
		services.delete(id);
	}

	@GetMapping
	@ApiOperation(produces = "application/json", notes = "Obtiene todos los carrito de compras que hay en el sistema", value = "Obtener todos los carrito de compras")
	public List<ShoppingCartDTO> findAll() {
		return services.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(produces = "application/json", notes = "El ID del carrito de compras es obligatorio, No enviar la imagen del producto", value = "Obtener un carrito de compras por su ID")
	public ShoppingCartDTO findByID(@PathVariable("id") String id) {
		return services.findByID(id);
	}

	@PostMapping
	@ApiOperation(value = "Crea un carrito de compras", notes = "No enviar la imagen del producto", produces = "application/json")
	public ShoppingCartDTO create(@Valid @RequestBody ShoppingCartDTO dto) {
		return services.create(dto);
	}

	@PutMapping
	@ApiOperation(value = "Actualiza un carrito de compras", produces = "application/json", notes = "El id del carrito de compras es obligatorio")
	public ShoppingCartDTO update(@Valid @RequestBody ShoppingCartDTO dto) {
		return services.update(dto);
	}

}
