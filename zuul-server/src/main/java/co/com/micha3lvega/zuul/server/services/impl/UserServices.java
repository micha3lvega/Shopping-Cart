package co.com.micha3lvega.zuul.server.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.micha3lvega.product.commons.dto.UserDTO;
import co.com.micha3lvega.zuul.server.rest.client.UserRestClient;
import co.com.micha3lvega.zuul.server.services.IUserService;

@Service
public class UserServices implements IUserService {
	
	@Autowired
	private UserRestClient client;

	@Override
	public UserDTO login(String email, String password) {
		return client.login(email, password);
	}

	@Override
	public UserDTO findByEmail(String email) {
		return client.findByEmail(email);
	}

}
