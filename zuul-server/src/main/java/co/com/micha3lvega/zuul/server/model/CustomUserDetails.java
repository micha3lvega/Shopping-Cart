package co.com.micha3lvega.zuul.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import co.com.micha3lvega.product.commons.dto.StateDTO;
import co.com.micha3lvega.product.commons.dto.UserDTO;

public class CustomUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 6777227208169442402L;
	private UserDTO user;
	
	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.toString())));

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getState().equals(StateDTO.ACTIVE);
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getState().equals(StateDTO.ACTIVE);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getState().equals(StateDTO.ACTIVE);
	}

	@Override
	public boolean isEnabled() {
		return user.getState().equals(StateDTO.ACTIVE);
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

}
