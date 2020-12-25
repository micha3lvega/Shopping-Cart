package co.com.micha3lvega.product.services.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.micha3lvega.product.services.exception.ErrorInfo;
import co.com.micha3lvega.product.services.exception.brand.BrandExistException;
import co.com.micha3lvega.product.services.exception.brand.BrandNoExistException;

@ControllerAdvice
public class ErrorHandlerCustomException {

	@ExceptionHandler({ BrandExistException.class, BrandNoExistException.class })
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request, RuntimeException e) {

		ResponseStatus responseStatus = null;
		for (int i = 0; i < e.getClass().getAnnotations().length; i++) {

			Object object = e.getClass().getAnnotations()[i];
			if (object instanceof ResponseStatus) {
				responseStatus = (ResponseStatus) object;

				ErrorInfo errorInfo = new ErrorInfo(responseStatus.code().value(), responseStatus.reason(),
						request.getRequestURI());
				// return error info object with standard json
				return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
			}

		}

		return null;
	}

}
