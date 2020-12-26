package co.com.micha3lvega.product.services.dto;

import java.io.InputStream;
import java.io.Serializable;

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
@JsonPropertyOrder({ "id", "name", "type", "data" })
@ApiModel(description = "Imagen del producto", value = "Imagen del producto")
public class ImageDTO implements Serializable {

	private static final long serialVersionUID = -3388733019000246496L;

	@ApiModelProperty(required = true, value = "ID de la imagen", example = "5fe56033ebee2c5f3247c47e", accessMode = AccessMode.READ_ONLY)
	private String id;

	@ApiModelProperty(value = "Tamaño de la imagen", example = "1246841", accessMode = AccessMode.READ_ONLY)
	private Long size;

	@ApiModelProperty(required = true, value = "Nombre de la imagen", example = "Producto de ejemplo")
	private String name;

	@ApiModelProperty(required = true, value = "Data base64 de la imagen", example = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABVYAAAM.....")
	private InputStream data;

	@ApiModelProperty(required = true, value = "Content Type de la imagen", example = "PNG,JPG")
	private String contentType;
	
}
