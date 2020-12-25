package co.com.micha3lvega.product.services.exception.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La marca que busca no existe")
public class BrandNoExistException extends RuntimeException {

	private static final long serialVersionUID = 4793137409845098089L;

}
