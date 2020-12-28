package co.com.micha3lvega.user.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Usuario o contraseña incorrectos")
public class UserLoginException extends RuntimeException {

	private static final long serialVersionUID = 8601660649954190958L;

}
