package co.com.micha3lvega.product.services.dto;

import java.io.Serializable;
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
@JsonPropertyOrder({ "id", "name", "create_date", "last_update" })
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 5868687704869054058L;

	private String id;
	private String name;

	private Date createDate;
	private Date lastUpdate;

}
