package co.com.micha3lvega.customer.services.exception.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;


@ControllerAdvice
public class ErrorHandlerCustom {

	@ExceptionHandler(FeignException.class)
	public String methodArgumentNotValidException(FeignException e,  HttpServletResponse response) {
		
		response.setStatus(e.status());
		
		return e.getMessage();
	}

}
