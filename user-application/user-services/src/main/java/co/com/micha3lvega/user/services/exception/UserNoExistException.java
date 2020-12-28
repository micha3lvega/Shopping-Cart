package co.com.micha3lvega.user.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El usuario que busca no existe")
public class UserNoExistException extends RuntimeException {

	private static final long serialVersionUID = 3147914061262344023L;

}
