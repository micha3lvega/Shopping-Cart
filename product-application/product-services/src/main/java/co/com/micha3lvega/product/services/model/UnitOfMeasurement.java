package co.com.micha3lvega.product.services.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

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
public class UnitOfMeasurement implements Serializable {

	private static final long serialVersionUID = -2174377383383525211L;

	@Id
	private String id;
	
	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastUpdate;

}
