package co.com.micha3lvega.product.services.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "Unida de medida del producto", value = "Unidad de medida")
public class UnitOfMeasurementDTO extends BasicObjectDTO {

	private static final long serialVersionUID = -356476957104031334L;
}
