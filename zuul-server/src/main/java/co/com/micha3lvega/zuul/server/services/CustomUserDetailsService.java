package co.com.micha3lvega.zuul.server.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import co.com.micha3lvega.zuul.server.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDTO user = service.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found!!");
		}

		CustomUserDetails customUserDetails = new CustomUserDetails(user.getEmail(), user.getPassword(), Collections.emptyList());
		customUserDetails.setUser(user);
		
		return customUserDetails;
		
	}

}
