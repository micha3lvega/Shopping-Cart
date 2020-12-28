package co.com.micha3lvega.country.services.model;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Traduciones del pais", value = "Traduciones")
@JsonPropertyOrder({ "de", "es", "fr", "ja", "it", "br", "pt", "nl", "hr", "fa" })
public class Translations implements Serializable {

	private static final long serialVersionUID = -2938198858287985234L;

	@JsonProperty("de")
	private String de;

	@JsonProperty("es")
	private String es;

	@JsonProperty("fr")
	private String fr;

	@JsonProperty("ja")
	private String ja;

	@JsonProperty("it")
	private String it;

	@JsonProperty("br")
	private String br;

	@JsonProperty("pt")
	private String pt;

	@JsonProperty("nl")
	private String nl;

	@JsonProperty("hr")
	private String hr;

	@JsonProperty("fa")
	private String fa;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
