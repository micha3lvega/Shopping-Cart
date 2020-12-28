package co.com.micha3lvega.country.services.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Currency implements Serializable {

	private static final long serialVersionUID = -8234089146615325934L;

	private String code;

	private String name;

	private String symbol;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
