package co.com.micha3lvega.customer.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El cliente que busca no existe")
public class CustomerNotExistException extends RuntimeException{

	private static final long serialVersionUID = -6425383030331523195L;

}
