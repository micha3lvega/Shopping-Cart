package co.com.micha3lvega.zuul.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import co.com.micha3lvega.zuul.server.rest.client.UserRestClient;

/**
 * Clase encargada de conectarse con el servicio de user-services y obtener la
 * informacon del usuario
 * 
 * @author micha3lvega
 *
 */
@Service("userAuthService")
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserRestClient userRestClient;

	/**
	 * metodo encargado de obtener la informacion del usuario
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {

		// Buscar el usuario
		UserDTO user = userRestClient.findByEmail(username);

		// Validar que no venga nulo
		if (user == null) {
			throw new UsernameNotFoundException("User not found!!");
		}

		// llenar los roles del usuario
		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.name())));

		// Retornar la informacion del usuario
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);

	}

}
