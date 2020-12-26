package co.com.micha3lvega.product.services.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Marca del producto", value = "Marca")
@JsonPropertyOrder({ "id", "name", "create_date", "last_update" })
public class BrandDTO implements Serializable {

	private static final long serialVersionUID = -2576484819029903329L;

	@ApiModelProperty(required = true, value = "ID de la marca", example = "5fe56033ebee2c5f3247c47e", accessMode = AccessMode.READ_ONLY)
	private String id;

	@NotNull(message = "El nombre es obligatorio")
	@Size(min = 2, message = "El nombre de la marca es muy corto")
	@ApiModelProperty(required = true, value = "Nombre de la marca", example = "Marca de ejemplo")
	private String name;

	@ApiModelProperty(value = "Fecha de creacion de la marca", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "Fecha de ultima actualizacion de la marca", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY)
	private Date lastUpdate;

}
