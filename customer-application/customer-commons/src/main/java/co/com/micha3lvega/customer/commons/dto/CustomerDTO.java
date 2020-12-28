package co.com.micha3lvega.customer.commons.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user", "address", "create_date", "last_update" })
@ApiModel(description = "Cliente", value = "Cliente")
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 6693289087799341590L;

	@ApiModelProperty(required = true, value = "ID del cliente", example = "5fe56033ebee2c5f3247c47e", accessMode = AccessMode.READ_ONLY, hidden = true)
	private String id;

	@Valid
	@NotNull(message = "El usuario es necesario")
	private UserDTO user;

	@Valid
	@NotNull(message = "La direccion es necesaria")
	private AddressDTO address;

	@ApiModelProperty(value = "Fecha de creacion del cliente", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY, hidden = true)
	private Date createDate;

	@ApiModelProperty(value = "Fecha de ultima actualizacion del cliente", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY, hidden = true)
	private Date lastUpdate;

}
