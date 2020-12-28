package co.com.micha3lvega.user.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe un usuario con este correo")
public class UserEmailExistException extends RuntimeException {

	private static final long serialVersionUID = -4330542485323974973L;

}
