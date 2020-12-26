package co.com.micha3lvega.product.services.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "sku", "name", "image", "description", "state", "price", "discount", "stock", "min_stock",
		"brand", "category", "unit_of_measurement", "create_date", "last_update" })
public class Product implements Serializable {

	private static final long serialVersionUID = -7549976582015291017L;

	@Transient
	public static final String SEQUENCE_NAME = "product_code_sequence";

	@Id
	private String id;

	@Indexed(direction = IndexDirection.ASCENDING)
	private Long sku;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;

	private Image image;

	private String description;

	private State state;

	private BigDecimal price;

	private BigDecimal discount;

	private Long stock;

	private Long minStock;

	private Brand brand;

	private Category category;

	private UnitOfMeasurement unitOfMeasurement;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastUpdate;

}
