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
@ApiModel(description = "Lenguaje del pais", value = "Lenguaje")
@JsonPropertyOrder({ "iso639_1", "iso639_2", "name", "nativeName" })
public class LanguageDTO implements Serializable {

	private static final long serialVersionUID = -4574736250522574563L;

	@JsonProperty("iso639_1")
	private String iso6391;

	@JsonProperty("iso639_2")
	private String iso6392;

	@JsonProperty("name")
	private String name;

	@JsonProperty("nativeName")
	private String nativeName;

}
