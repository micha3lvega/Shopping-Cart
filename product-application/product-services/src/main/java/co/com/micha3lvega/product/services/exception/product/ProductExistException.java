package co.com.micha3lvega.product.services.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe un producto con este nombre")
public class ProductExistException extends RuntimeException {

	private static final long serialVersionUID = 3466350658441559228L;

}
