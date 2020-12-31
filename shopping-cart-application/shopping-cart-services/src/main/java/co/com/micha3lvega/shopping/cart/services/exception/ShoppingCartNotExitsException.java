package co.com.micha3lvega.shopping.cart.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El carrito de compras que busca no existe")
public class ShoppingCartNotExitsException extends RuntimeException {

	private static final long serialVersionUID = -7759547911680617998L;

}
