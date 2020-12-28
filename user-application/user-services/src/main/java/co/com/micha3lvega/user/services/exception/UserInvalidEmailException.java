package co.com.micha3lvega.user.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "El email es incorrecto")
public class UserInvalidEmailException extends RuntimeException {

	private static final long serialVersionUID = -6454926003391802826L;

}
