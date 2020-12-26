package co.com.micha3lvega.product.services.dto;

import java.io.InputStream;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "id", "name", "type", "data" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDTO implements Serializable {

	private static final long serialVersionUID = -3388733019000246496L;

	private String id;
	private Long size;
	private String name;
	private InputStream data;
	private String contentType;

}
