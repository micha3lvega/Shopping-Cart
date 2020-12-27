package co.com.micha3lvega.product.services.exception.image;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ha susesido un error procesando la imagen")
public class ImageIOException extends RuntimeException {

	private static final long serialVersionUID = 6884491677021586918L;

}
