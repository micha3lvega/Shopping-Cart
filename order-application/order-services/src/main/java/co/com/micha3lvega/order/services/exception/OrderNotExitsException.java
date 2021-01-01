package co.com.micha3lvega.order.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La marca que busca no existe")
public class OrderNotExitsException extends RuntimeException {

	private static final long serialVersionUID = 5910082192623709650L;

}
