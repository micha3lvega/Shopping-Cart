package co.com.micha3lvega.customer.services.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.country.commons.dto.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "city", "line", "line2", "country" })
public class Address implements Serializable {

	private static final long serialVersionUID = 6313438022545610463L;

	private String city;
	private String line;
	private String line2;
	private CountryDTO country;

}
