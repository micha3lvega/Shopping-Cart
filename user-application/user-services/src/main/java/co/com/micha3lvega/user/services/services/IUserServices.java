package co.com.micha3lvega.user.services.services;

import java.util.List;

import co.com.micha3lvega.product.commons.dto.UserDTO;

public interface IUserServices {

	List<UserDTO> findAll();

	UserDTO enable(String id);

	UserDTO disable(String id);

	UserDTO create(UserDTO dto);

	UserDTO update(UserDTO dto);

	UserDTO findById(String id);
	
	UserDTO findByEmail(String email);

	UserDTO login(String email, String password);

	UserDTO updatePassword(String id, String newPassword);

}
