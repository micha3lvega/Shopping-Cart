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

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Traduciones del pais", value = "Traduciones")
@JsonPropertyOrder({ "de", "es", "fr", "ja", "it", "br", "pt", "nl", "hr", "fa" })
public class Translations implements Serializable {

	private static final long serialVersionUID = -2938198858287985234L;

	private String de;

	private String es;

	private String fr;

	private String ja;

	private String it;

	private String br;

	private String pt;

	private String nl;

	private String hr;

	private String fa;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
