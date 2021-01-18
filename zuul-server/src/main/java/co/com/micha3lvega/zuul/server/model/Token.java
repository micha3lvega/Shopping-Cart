package co.com.micha3lvega.zuul.server.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonPropertyOrder({ "access_token", "refresh_token", "expires_in", "id_token", "token_type", "is_admin" })
public class Token implements Serializable {

	private static final long serialVersionUID = 4185163965680212375L;

	public static final String SECRET = Base64Utils
			.encodeToString("co.com.micha3lvega.zuul.server.services.impl.7d7c88e18af1b7cc2f6891724649bf64".getBytes());

	public static final String SECRET_REFRESH = Base64Utils
			.encodeToString("co.com.micha3lvega.zuul.server.services.impl.5fffcd770bd2c670ecfc411e".getBytes());

	public static final long EXPIRATION_DATE_ACCESS_TOKEN = 3600000L;
	public static final long EXPIRATION_REFRESH_ACCESS_TOKEN = 21600000L;

	public static final String TOKEN_TYPE = "Bearer";
	public static final String HEADER_STRING = "Authorization";

	@JsonProperty("create_date")
	private Date createDate;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonProperty("expires_in")
	private Long expiresIn;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("token_type")
	private String tokenType = Token.TOKEN_TYPE;

	@JsonProperty("token_id")
	private String tokenId;

	@JsonProperty("is_admin")
	private Boolean isAdmin = false;

	@JsonProperty("refresh_token_expires_in")
	private Long refreshTokenExpiresIn;

}
