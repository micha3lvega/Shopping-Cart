package co.com.micha3lvega.product.commons.dto;

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
public class SecurityDTO implements Serializable {

	private static final long serialVersionUID = 5150893965866840090L;

	private String accessToken;
	private AtomicLong deviceCode;

	private boolean accountNonLocked;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;

	private boolean enabled;

}
