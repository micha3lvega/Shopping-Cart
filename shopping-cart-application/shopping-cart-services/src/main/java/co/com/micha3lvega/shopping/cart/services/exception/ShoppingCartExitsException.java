package co.com.micha3lvega.shopping.cart.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe un carrito de compras para el usuario")
public class ShoppingCartExitsException extends RuntimeException {

	private static final long serialVersionUID = 5738203667887520717L;

}
