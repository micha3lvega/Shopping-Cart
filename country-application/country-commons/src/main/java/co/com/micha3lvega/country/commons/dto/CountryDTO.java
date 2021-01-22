package co.com.micha3lvega.country.commons.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(required = true, value = "ID del pais", example = "5fe56033ebee2c5f3247c47e", hidden = true)
	private String id;

	@NotNull
	@ApiModelProperty(required = true, value = "Nombre del pais", example = "Colombia")
	private String name;

	@JsonProperty("topLevelDomain")
	private List<String> topLevelDomain;

	@JsonProperty("alpha2Code")
	private String alpha2Code;

	@JsonProperty("alpha3Code")
	private String alpha3Code;

	@JsonProperty("callingCodes")
	private List<String> callingCodes;

	@JsonProperty("capital")
	private String capital;

	@JsonProperty("altSpellings")
	private List<String> altSpellings;

	@JsonProperty("region")
	private String region;

	@JsonProperty("subregion")
	private String subregion;

	@JsonProperty("population")
	private Long population;

	@JsonProperty("latlng")
	private List<Double> latlng;

	@JsonProperty("demonym")
	private String demonym;

	@JsonProperty("area")
	private Double area;

	@JsonProperty("gini")
	private Double gini;

	@JsonProperty("timezones")
	private List<String> timezones;

	@JsonProperty("borders")
	private List<String> borders;

	@JsonProperty("nativeName")
	private String nativeName;

	@JsonProperty("numericCode")
	private String numericCode;

	@JsonProperty("currencies")
	private List<CurrencyDTO> currencies;

	@JsonProperty("languages")
	private List<LanguageDTO> languages;

	@JsonProperty("translations")
	private TranslationsDTO translations;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("regionalBlocs")
	private List<RegionalBlocDTO> regionalBlocs;

	@JsonProperty("cioc")
	private String cioc;

}
