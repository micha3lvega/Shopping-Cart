package co.com.micha3lvega.customer.commons.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "city", "line", "line2", "country" })
@ApiModel(description = "Direccion", value = "Direccion")
public class AddressDTO implements Serializable {

	private static final long serialVersionUID = -6957629241929648996L;

	@Valid
	@NotNull(message = "La ciudad es necesaria")
	private String city;
	
	@Valid
	@NotNull(message = "La direccion es necesaria")
	private String line;
	
	private String line2;
	
	@Valid
	@NotNull(message = "El pais es necesario")
	private CountryDTO country;

}
