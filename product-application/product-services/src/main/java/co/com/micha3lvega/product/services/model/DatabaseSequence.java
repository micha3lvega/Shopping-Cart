package co.com.micha3lvega.product.services.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSequence implements Serializable {

	private static final long serialVersionUID = 2571510205837596227L;

	@Id
	private String id;
	private Long seq;

}