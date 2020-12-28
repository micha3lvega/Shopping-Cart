package co.com.micha3lvega.product.commons.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@ApiModel(description = "Usuario del sistema", value = "Usuario")
@JsonPropertyOrder({ "id", "name", "lastName", "email", "mobile", "password", "roles", "state", "create_date",
		"last_update" })
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 274033024534254454L;

	@ApiModelProperty(required = true, value = "ID del usuario", example = "5fe56033ebee2c5f3247c47e", hidden = true)
	private String id;

	@NotNull(message = "El nombre es obligatorio")
	@Size(min = 2, message = "El nombre del usuario es muy corto")
	@ApiModelProperty(required = true, value = "Nombre del usuario", example = "Jhon")
	private String name;

	@NotNull(message = "El apellido es obligatorio")
	@Size(min = 2, message = "El apellido del usuario es muy corto")
	@ApiModelProperty(required = true, value = "Apellido del usuario", example = "Doe")
	private String lastName;

	@Email(message = "El correo es invalido")
	@NotNull(message = "El correo es obligatorio")
	@ApiModelProperty(required = true, value = "Correo del usuario", example = "me@example.com")
	private String email;

	@NotNull(message = "El telefono es obligatorio")
	@Size(min = 2, message = "El telefono del usuario es muy corto")
	@ApiModelProperty(required = true, value = "Telefono del usuario", example = "3045952623")
	private String mobile;

	@NotNull(message = "la contraseña es obligatoria")
	@Size(min = 6, message = "la contraseña del usuario es muy corto")
	@ApiModelProperty(required = true, value = "Contraseña del usuario", example = "StrongPaSSWoRd")
	private String password;

	@NotNull(message = "El rol del usuario es obligatoria")
	@ApiModelProperty(required = true, value = "Roles del usuario", example = "[CUSTOMER]")
	private List<RolDTO> roles;

	@ApiModelProperty(value = "Estado del usuario", example = "ACTIVE")
	private StateDTO state;

	@ApiModelProperty(value = "Fecha de creacion del producto", example = "2020-12-25T23:08:29.735Z", hidden = true)
	private Date createDate;

	@ApiModelProperty(value = "Fecha de ultima actualizacion del producto", example = "2020-12-25T23:08:29.735Z", hidden = true)
	private Date lastUpdate;

}
