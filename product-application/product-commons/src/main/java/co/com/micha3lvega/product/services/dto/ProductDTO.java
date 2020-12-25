package co.com.micha3lvega.product.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "sku", "name", "image", "description", "state", "price", "discount", "stock", "min_stock",
		"brand", "category", "unit_of_measurement", "create_date", "last_update" })
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = -6438813507103704927L;

	private String id;
	private Long sku;

	private String name;
	private ImageDTO image;
	private String description;

	private StateDTO state;

	private BigDecimal price;
	private BigDecimal discount;

	private Long stock;
	private Long minStock;

	private BrandDTO brand;
	private CategoryDTO category;
	private UnitOfMeasurementDTO unitOfMeasurement;

	private Date createDate;
	private Date lastUpdate;

}
