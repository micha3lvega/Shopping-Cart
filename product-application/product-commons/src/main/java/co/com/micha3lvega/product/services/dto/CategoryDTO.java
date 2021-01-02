package co.com.micha3lvega.product.services.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "Categoria del producto", value = "Categoria")
public class CategoryDTO extends BasicObjectDTO {

	private static final long serialVersionUID = 5868687704869054058L;



}
