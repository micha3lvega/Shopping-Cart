package co.com.micha3lvega.order.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.order.commons.dto.OrderStatusDTO;
import co.com.micha3lvega.product.services.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "customer", "products", "state", "create_date", "last_update" })
public class Order implements Serializable {

	private static final long serialVersionUID = 542474670300628351L;

	@Id
	private String id;
	private CustomerDTO customer;
	private List<ProductDTO> products;
	
	private OrderStatusDTO state;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastUpdate;

}
