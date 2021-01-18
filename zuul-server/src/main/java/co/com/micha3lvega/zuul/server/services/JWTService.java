package co.com.micha3lvega.zuul.server.services;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import co.com.micha3lvega.zuul.server.model.Token;
import io.jsonwebtoken.Claims;

public interface JWTService {

	Token create(Authentication auth) throws IOException;

	boolean validate(String token);

	Claims getClaims(String token);

	String getUsername(String token);

	Collection<GrantedAuthority> getRoles(String token) throws IOException;

	String resolve(String token);

}
