package co.com.micha3lvega.user.services.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "device_code", "accoun_non_locked", "account_non_expired",
		"credentials_non_expired" })
public class Security implements Serializable {

	private static final long serialVersionUID = 3189607203683769978L;

	private String accessToken;
	private AtomicLong deviceCode;

	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;

	private boolean enabled;

}
