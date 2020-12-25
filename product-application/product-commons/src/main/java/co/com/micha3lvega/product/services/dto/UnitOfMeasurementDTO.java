package co.com.micha3lvega.product.services.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class UnitOfMeasurementDTO implements Serializable {

	private static final long serialVersionUID = -356476957104031334L;

	private String id;

	@NotNull(message = "El nombre es obligatorio")
	@Size(min = 2, message = "El nombre de la unidad de medida es muy corto")
	private String name;

	private Date createDate;
	private Date lastUpdate;
}
