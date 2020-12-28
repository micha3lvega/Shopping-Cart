package co.com.micha3lvega.country.commons.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
@ApiModel(description = "Paises", value = "Pais")
@JsonPropertyOrder({ "id", "name", "topLevelDomain", "alpha2Code", "alpha3Code", "callingCodes", "capital",
		"altSpellings", "region", "subregion", "population", "latlng", "demonym", "area", "gini", "timezones",
		"borders", "nativeName", "numericCode", "currencies", "languages", "translations", "flag", "regionalBlocs",
		"cioc" })
public class CountryDTO implements Serializable {

	private static final long serialVersionUID = -7641671328105268425L;

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("topLevelDomain")
	private List<String> topLevelDomain = null;

	@JsonProperty("alpha2Code")
	private String alpha2Code;

	@JsonProperty("alpha3Code")
	private String alpha3Code;

	@JsonProperty("callingCodes")
	private List<String> callingCodes = null;

	@JsonProperty("capital")
	private String capital;

	@JsonProperty("altSpellings")
	private List<String> altSpellings = null;

	@JsonProperty("region")
	private String region;

	@JsonProperty("subregion")
	private String subregion;

	@JsonProperty("population")
	private Long population;

	@JsonProperty("latlng")
	private List<Double> latlng = null;

	@JsonProperty("demonym")
	private String demonym;

	@JsonProperty("area")
	private Double area;

	@JsonProperty("gini")
	private Double gini;

	@JsonProperty("timezones")
	private List<String> timezones = null;

	@JsonProperty("borders")
	private List<String> borders = null;

	@JsonProperty("nativeName")
	private String nativeName;

	@JsonProperty("numericCode")
	private String numericCode;

	@JsonProperty("currencies")
	private List<CurrencyDTO> currencies = null;

	@JsonProperty("languages")
	private List<LanguageDTO> languages = null;

	@JsonProperty("translations")
	private TranslationsDTO translations;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("regionalBlocs")
	private List<RegionalBlocDTO> regionalBlocs = null;

	@JsonProperty("cioc")
	private String cioc;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
