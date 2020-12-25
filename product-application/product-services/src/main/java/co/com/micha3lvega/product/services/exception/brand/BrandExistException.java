package co.com.micha3lvega.product.services.exception.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe una marca con este nombre")
public class BrandExistException extends RuntimeException {

	private static final long serialVersionUID = -4065653233110381228L;

}
