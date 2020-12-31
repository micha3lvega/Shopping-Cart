package co.com.micha3lvega.shopping.cart.services.services;

import java.util.List;

import co.com.micha3lvega.shopping.cart.commons.dto.ShoppingCartDTO;

public interface IShoppingCartServices {

	void delete(String id);

	List<ShoppingCartDTO> findAll();

	ShoppingCartDTO findByID(String id);

	ShoppingCartDTO create(ShoppingCartDTO dto);

	ShoppingCartDTO update(ShoppingCartDTO dto);

}
