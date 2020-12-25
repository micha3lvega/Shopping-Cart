package co.com.micha3lvega.product.services.exception.unit.of.measurement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Ya existe una categoria con este nombre")
public class UnitOfMeasurementExistException extends RuntimeException {

	private static final long serialVersionUID = 4452029835759660785L;

}
