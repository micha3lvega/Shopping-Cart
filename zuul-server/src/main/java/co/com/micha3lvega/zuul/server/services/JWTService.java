package co.com.micha3lvega.zuul.server.services;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import co.com.micha3lvega.zuul.server.model.Token;
import io.jsonwebtoken.Claims;

/**
 * Clase encargada del manejo de los metodos de la administracion del token
 * 
 * @author micha3lvega
 *
 */
public interface JWTService {

	/**
	 * Genera el token
	 * 
	 * @param auth
	 * @return
	 * @throws IOException
	 */
	Token create(Authentication auth) throws IOException;

	/**
	 * Valida el token
	 * 
	 * @param token
	 * @return si el token es valido o no
	 */
	boolean validate(String token);

	/**
	 * Devuelve las reclamaciones del token
	 * 
	 * @param token
	 * @return
	 */
	Claims getClaims(String token);

	/**
	 * Obtiene el nombre de usuario dentro del token
	 * 
	 * @param token
	 * @return
	 */
	String getUsername(String token);

	/**
	 * devuelve los roles del usuario
	 * 
	 * @param token
	 * @return los roles que estan dentro del token
	 * @throws IOException
	 */
	Collection<GrantedAuthority> getRoles(String token) throws IOException;

	/**
	 * Metodo encargado de eliminar y obtener unicamente el token dentro del
	 * parametro de autenticacion
	 *
	 * @param token
	 * @return
	 */
	String resolve(String token);

}
