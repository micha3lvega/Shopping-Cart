package co.com.micha3lvega.country.services.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "countries")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Pais", value = "Pais")
@JsonPropertyOrder({ "id", "name", "topLevelDomain", "alpha2Code", "alpha3Code", "callingCodes", "capital",
		"altSpellings", "region", "subregion", "population", "latlng", "demonym", "area", "gini", "timezones",
		"borders", "nativeName", "numericCode", "currencies", "languages", "translations", "flag", "regionalBlocs",
		"cioc" })
public class Country implements Serializable {

	private static final long serialVersionUID = -2617238303335601963L;

	@Id
	private String id;

	private String name;

	private List<String> topLevelDomain = null;

	private String alpha2Code;

	private String alpha3Code;

	private List<String> callingCodes = null;

	private String capital;

	private List<String> altSpellings = null;

	private String region;

	private String subregion;

	private Long population;

	private List<Double> latlng = null;

	private String demonym;

	private Double area;

	private Double gini;

	private List<String> timezones = null;

	private List<String> borders = null;

	private String nativeName;

	private String numericCode;

	private List<Currency> currencies = null;

	private List<Language> languages = null;

	private Translations translations;

	private String flag;

	private List<RegionalBloc> regionalBlocs = null;

	private String cioc;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
