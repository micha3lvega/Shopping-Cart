package co.com.micha3lvega.product.services.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe una categoria con este nombre")
public class CategoryExistException extends RuntimeException {

	private static final long serialVersionUID = -4065653233110381228L;

}
