package co.com.micha3lvega.shopping.cart.commons.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.product.services.dto.ProductDTO;
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
@ApiModel(description = "Carrito de compras del usuario", value = "Carrito de compras")
@JsonPropertyOrder({ "id", "customer", "products", "create_date", "last_update" })
public class ShoppingCartDTO implements Serializable {

	private static final long serialVersionUID = -25787460908161985L;

	@ApiModelProperty(required = true, value = "ID del carrito de compras", example = "5fe56033ebee2c5f3247c47e", hidden = true)
	private String id;

	@Valid
	@NotNull(message = "El cliente es obligatorio")
	private CustomerDTO customer;

	@Valid
	@NotNull(message = "El carrito de compras debe tener al menos un producto")
	private List<ProductDTO> products;

	@ApiModelProperty(value = "Fecha de creacion del carrito de compras", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(value = "Fecha de ultima actualizacion del carrito de compras", example = "2020-12-25T23:08:29.735Z", hidden = true)
	private Date lastUpdate;

}
