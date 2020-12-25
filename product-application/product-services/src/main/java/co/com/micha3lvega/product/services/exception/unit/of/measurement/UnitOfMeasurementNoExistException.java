package co.com.micha3lvega.product.services.exception.unit.of.measurement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La unidad de medidad que busca no existe")
public class UnitOfMeasurementNoExistException extends RuntimeException {

	private static final long serialVersionUID = 3169351214148958119L;

}
