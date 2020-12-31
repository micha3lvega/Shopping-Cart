package co.com.micha3lvega.shopping.cart.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.shopping.cart.commons.dto.ShoppingCartDTO;
import co.com.micha3lvega.shopping.cart.services.exception.ShoppingCartExitsException;
import co.com.micha3lvega.shopping.cart.services.exception.ShoppingCartNotExitsException;
import co.com.micha3lvega.shopping.cart.services.model.ShoppingCart;
import co.com.micha3lvega.shopping.cart.services.repository.ShoppingCartRepository;
import co.com.micha3lvega.shopping.cart.services.services.IShoppingCartServices;

@Service
public class ShoppingCartServices implements IShoppingCartServices {

	@Autowired
	private ShoppingCartRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public void delete(String id) {

		// Buscar el carrito de compras si no existe retornar una excepcion
		ShoppingCart shoppingCart = repository.findById(id).orElseThrow(ShoppingCartNotExitsException::new);

		// Eliminar el carrito de compras
		repository.delete(shoppingCart);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingCartDTO> findAll() {
		return repository.findAll().stream().map(cart -> {
			return mapper.map(cart, ShoppingCartDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ShoppingCartDTO findByID(String id) {

		// Buscar el carrito de compras si no existe retornar una excepcion
		ShoppingCart shoppingCart = repository.findById(id).orElseThrow(ShoppingCartNotExitsException::new);

		return mapper.map(shoppingCart, ShoppingCartDTO.class);

	}

	@Override
	@Transactional
	public ShoppingCartDTO create(ShoppingCartDTO dto) {

		// Buscar si existe ya un carro de compras para el cliente
		ShoppingCart existCart = repository.findByCustomerId(dto.getCustomer().getId());

		// Si ya hay un carrito de compras retornar una excepcion
		if (existCart != null) {
			throw new ShoppingCartExitsException();
		}

		// Crear el carrito de compras
		ShoppingCart newCart = repository.insert(mapper.map(dto, ShoppingCart.class));

		return mapper.map(newCart, ShoppingCartDTO.class);

	}

	@Override
	@Transactional
	public ShoppingCartDTO update(ShoppingCartDTO dto) {

		// Buscar el carrito de compras si no existe retornar una excepcion
		ShoppingCart shoppingCart = repository.findById(dto.getId()).orElse(null);

		// Si el carrito de compras no existe retornar una excepcion
		if (shoppingCart == null) {
			throw new ShoppingCartNotExitsException();
		}

		// Actualizar
		shoppingCart = repository.save(mapper.map(dto, ShoppingCart.class));

		return mapper.map(shoppingCart, ShoppingCartDTO.class);

	}

}
