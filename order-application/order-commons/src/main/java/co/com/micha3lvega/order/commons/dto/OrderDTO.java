package co.com.micha3lvega.order.commons.dto;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Orden", value = "Orden")
@JsonPropertyOrder({ "id", "customer", "products", "state", "create_date", "last_update" })
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1411810572738131149L;

	@ApiModelProperty(required = true, value = "ID de la orden", example = "5fe56033ebee2c5f3247c47e", hidden = true)
	private String id;

	@Valid
	@NotNull(message = "El cliente es obligatorio")
	private CustomerDTO customer;

	@Valid
	@NotNull(message = "La orden debe tener al menos un producto")
	private List<ProductDTO> products;

	@ApiModelProperty(value = "Estado de la orden", example = "NOT_SEND")
	private OrderStatusDTO state;

	@ApiModelProperty(value = "Fecha de creacion del producto", example = "2020-12-25T23:08:29.735Z", hidden = true)
	private Date createDate;

	@ApiModelProperty(value = "Fecha de ultima actualizacion del producto", example = "2020-12-25T23:08:29.735Z", hidden = true)
	private Date lastUpdate;

}
