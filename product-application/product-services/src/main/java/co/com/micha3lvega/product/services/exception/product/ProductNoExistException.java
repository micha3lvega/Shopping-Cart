package co.com.micha3lvega.product.services.exception.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El producto que busca no existe")
public class ProductNoExistException extends RuntimeException {

	private static final long serialVersionUID = -2707739439513092087L;

}
