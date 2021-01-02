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
@ApiModel(description = "Regiones del pais", value = "Regiones")
@JsonPropertyOrder({ "acronym", "name", "otherAcronyms", "otherNames" })
public class RegionalBlocDTO implements Serializable {

	private static final long serialVersionUID = 4893742165681278217L;

	@JsonProperty("acronym")
	private String acronym;

	@JsonProperty("name")
	private String name;


}
