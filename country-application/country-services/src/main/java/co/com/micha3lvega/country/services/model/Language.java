package co.com.micha3lvega.country.services.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel(description = "Lenguaje del pais", value = "Lenguaje")
@JsonPropertyOrder({ "iso639_1", "iso639_2", "name", "nativeName" })
public class Language implements Serializable {

	private static final long serialVersionUID = -4574736250522574563L;

	private String iso6391;

	private String iso6392;

	private String name;

	private String nativeName;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
