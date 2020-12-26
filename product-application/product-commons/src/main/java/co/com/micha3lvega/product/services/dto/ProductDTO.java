package co.com.micha3lvega.product.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@ApiModel(description = "Producto", value = "Producto")
@JsonPropertyOrder({ "id", "sku", "name", "image", "description", "state", "price", "discount", "stock", "min_stock",
		"brand", "category", "unit_of_measurement", "create_date", "last_update" })
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = -6438813507103704927L;

	@ApiModelProperty(position = 1, required = true, value = "ID de la producto", example = "5fe56033ebee2c5f3247c47e", accessMode = AccessMode.READ_ONLY)
	private String id;

	@ApiModelProperty(position = 2, required = true, value = "SKU de la producto", example = "1", accessMode = AccessMode.READ_ONLY)
	private Long sku;

	@NotNull(message = "El nombre es obligatorio")
	@Size(min = 2, message = "El nombre del producto es muy corto")
	@ApiModelProperty(position = 3, required = true, value = "Nombre del producto", example = "Producto de ejemplo")
	private String name;

	private ImageDTO image;

	@Size(min = 2, message = "La descripcion del producto es muy corto")
	@ApiModelProperty(position = 4, value = "Descripcion del producto", example = "Descripcion de ejemplo")
	private String description;

	@Size(min = 2, message = "El estado del producto es muy corto")
	@ApiModelProperty(position = 4, value = "Estado del producto", example = "ACTIVE,INACTIVE")
	private StateDTO state;

	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0.0", inclusive = false)
	@ApiModelProperty(position = 5, value = "Precio del producto", example = "1")
	private BigDecimal price;

	@Digits(integer = 3, fraction = 2)
	@DecimalMin(value = "0.0", inclusive = false)
	@ApiModelProperty(position = 6, value = "Descuento del producto", example = "1")
	private BigDecimal discount;

	@Min(value = 0, message = "El stock debe ser minimo de 0")
	@ApiModelProperty(position = 7, value = "Stock del producto", example = "1")
	private Long stock;

	@Min(value = 0, message = "El stock minimo debe de ser 0")
	@ApiModelProperty(position = 8, value = "Stock minimo del producto", example = "1")
	private Long minStock;

	@Valid
	@NotNull(message = "La marca es obligatoria")
	private BrandDTO brand;

	@Valid
	@NotNull(message = "La categoria es obligatoria")
	private CategoryDTO category;

	@Valid
	@NotNull(message = "La unidad de medida es obligatoria")
	private UnitOfMeasurementDTO unitOfMeasurement;

	@ApiModelProperty(position = 9, value = "Fecha de creacion del producto", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY)
	private Date createDate;

	@ApiModelProperty(position = 10, value = "Fecha de ultima actualizacion del producto", example = "2020-12-25T23:08:29.735Z", accessMode = AccessMode.READ_ONLY)
	private Date lastUpdate;

}
