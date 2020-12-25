package co.com.micha3lvega.product.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The brand already exists!")
public class BrandExistException extends RuntimeException {

	private static final long serialVersionUID = -4065653233110381228L;

}
