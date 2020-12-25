package co.com.micha3lvega.product.services.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La categoria que busca no existe")
public class CategoryNoExistException extends RuntimeException {

	private static final long serialVersionUID = 4793137409845098089L;

}
