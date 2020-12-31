package co.com.micha3lvega.product.services.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.com.micha3lvega.product.services.exception.ErrorInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> methodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException e) {

		// get spring errors
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		// convert errors to standard string
		// convert errors to standard string
		StringBuilder errorMessage = new StringBuilder();

		for (int i = 0; i < fieldErrors.size(); i++) {
			errorMessage.append(fieldErrors.get(i).getDefaultMessage());
			if (fieldErrors.size() > 1 && i < fieldErrors.size() - 1) {
				errorMessage.append(",");
			}
		}

		// return error info object with standard json
		ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), errorMessage.toString(),
				request.getRequestURI());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);

	}

}
