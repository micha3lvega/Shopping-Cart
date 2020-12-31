package co.com.micha3lvega.customer.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED, reason = "La informacion recibida es insuficiente")
public class CustomerInvalidInformacionException extends RuntimeException {

	private static final long serialVersionUID = -5632814370185553377L;

}
