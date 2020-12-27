package co.com.micha3lvega.product.services.exception.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No ha selecionado una imagen")
public class NoImageException extends RuntimeException{

	private static final long serialVersionUID = 6651178121760484871L;

}
