package co.com.micha3lvega.customer.services.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "user", "address", "create_date", "last_update" })
public class Customer implements Serializable {

	private static final long serialVersionUID = -4471789291129964655L;

	@Id
	private String id;

	private UserDTO user;

	private Address address;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastUpdate;

}
