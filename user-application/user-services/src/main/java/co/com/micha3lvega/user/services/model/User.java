package co.com.micha3lvega.user.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@JsonPropertyOrder({ "id", "name", "lastName", "email", "mobile", "password", "roles", "state", "create_date",
		"last_update" })
public class User implements Serializable {

	private static final long serialVersionUID = 6113377861160899416L;

	@Id
	private String id;

	private String name;

	private String lastName;

	@Indexed(direction = IndexDirection.ASCENDING, unique = true)
	private String email;

	private String mobile;

	private String password;

	private List<Rol> roles;

	private State state;

	private String clientId;

	private String clientSecret;

	private Security security;

	@CreatedDate
	private Date createDate;

	@LastModifiedDate
	private Date lastUpdate;

}
