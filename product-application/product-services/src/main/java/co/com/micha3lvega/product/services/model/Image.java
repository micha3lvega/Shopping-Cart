package co.com.micha3lvega.product.services.model;

import java.io.Serializable;
import org.bson.types.Binary;

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
@Document(collection = "images")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "size", "name", "data", "contentType", "create_date", "last_update" })
public class Image implements Serializable {

	private static final long serialVersionUID = -8009473838700474340L;

	private Long size;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;

	private Binary data;

	private String contentType;

}
