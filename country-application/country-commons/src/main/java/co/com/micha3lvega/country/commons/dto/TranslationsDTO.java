package co.com.micha3lvega.country.commons.dto;

import java.io.Serializable;

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
@ApiModel(description = "Traducciones del pais", value = "Traducciones")
@JsonPropertyOrder({ "de", "es", "fr", "ja", "it", "br", "pt", "nl", "hr", "fa" })
public class TranslationsDTO implements Serializable {

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

}
