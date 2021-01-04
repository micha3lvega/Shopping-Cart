package co.com.micha3lvega.zuul.server.services;

import co.com.micha3lvega.product.commons.dto.UserDTO;

public interface IUserService {

	public UserDTO findByEmail(String email);

	public UserDTO login(String email, String password);

}
