package co.com.micha3lvega.country.commons.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
	
	@ApiModelProperty(required = true, value = "Codigo de la moneda", example = "Peso")
	@JsonProperty("code")
	private String code;

	@NotNull
	@ApiModelProperty(required = true, value = "Nombre de la moneda", example = "Peso")
	@JsonProperty("name")
	private String name;

	@NotNull
	@ApiModelProperty(required = true, value = "Simbolo de la moneda", example = "$")
	@JsonProperty("symbol")
	private String symbol;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
