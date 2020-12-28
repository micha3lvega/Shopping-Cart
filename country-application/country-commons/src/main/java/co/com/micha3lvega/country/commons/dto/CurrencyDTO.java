package co.com.micha3lvega.country.commons.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author micha3lvega
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Moneda del pais", value = "Moneda")
@JsonPropertyOrder({ "code", "name", "symbol" })
public class CurrencyDTO implements Serializable {

	private static final long serialVersionUID = -8234089146615325934L;

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("symbol")
	private String symbol;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
