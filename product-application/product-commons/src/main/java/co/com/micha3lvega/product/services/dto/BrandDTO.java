package co.com.micha3lvega.product.services.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Marca del producto", value = "Marca")
public class BrandDTO extends BasicObjectDTO {

	private static final long serialVersionUID = -2576484819029903329L;

}
