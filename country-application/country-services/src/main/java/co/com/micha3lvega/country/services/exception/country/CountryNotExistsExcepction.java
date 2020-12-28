package co.com.micha3lvega.country.services.exception.country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El pais que busca no existe")
public class CountryNotExistsExcepction extends RuntimeException {

	private static final long serialVersionUID = 578806977506156305L;

}
