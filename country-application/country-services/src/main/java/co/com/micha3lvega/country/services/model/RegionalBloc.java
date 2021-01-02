package co.com.micha3lvega.country.services.model;

import java.io.Serializable;

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
@ApiModel(description = "Regiones del pais", value = "Regiones")
@JsonPropertyOrder({ "acronym", "name", "otherAcronyms", "otherNames" })
public class RegionalBloc implements Serializable {

	private static final long serialVersionUID = 4893742165681278217L;

	private String acronym;

	private String name;

}
