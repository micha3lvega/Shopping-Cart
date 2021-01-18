package co.com.micha3lvega.user.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.product.commons.dto.SecurityDTO;
import co.com.micha3lvega.product.commons.dto.StateDTO;
import co.com.micha3lvega.product.commons.dto.UserDTO;
import co.com.micha3lvega.product.commons.util.RandomString;
import co.com.micha3lvega.user.services.exception.UserEmailExistException;
import co.com.micha3lvega.user.services.exception.UserInvalidEmailException;
import co.com.micha3lvega.user.services.exception.UserLoginException;
import co.com.micha3lvega.user.services.exception.UserNoExistException;
import co.com.micha3lvega.user.services.model.State;
import co.com.micha3lvega.user.services.model.User;
import co.com.micha3lvega.user.services.repository.UserRepository;
import co.com.micha3lvega.user.services.services.IUserServices;

@Service
public class UserServices implements IUserServices {

	@Autowired
	private UserRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private Pbkdf2PasswordEncoder encoder;

	@Autowired
	private SecurityDTO securityDTO;

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		return repository.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public UserDTO enable(String id) {

		// Buscar el usuario si no existe retornar una excepcion
		User user = repository.findById(id).orElseThrow(UserNoExistException::new);

		// Cambiar el estado
		user.setState(State.ACTIVE);

		// Actualizar
		user = repository.save(user);

		return mapper.map(user, UserDTO.class);

	}

	@Override
	@Transactional
	public UserDTO disable(String id) {

		// Buscar el usuario si no existe retornar una excepcion
		User user = repository.findById(id).orElseThrow(UserNoExistException::new);

		// Cambiar el estado
		user.setState(State.INACTIVE);

		// Actualizar
		user = repository.save(user);

		return mapper.map(user, UserDTO.class);
	}

	@Override
	@Transactional
	public UserDTO create(UserDTO dto) {

		// Validar el correo
		if (dto == null || dto.getEmail() == null || dto.getEmail().isEmpty() || dto.getPassword() == null
				|| dto.getPassword().isEmpty()) {
			throw new UserInvalidEmailException();
		}

		// Normalizar el correo
		dto.setEmail(dto.getEmail().toLowerCase());

		// Codificar contraseña
		dto.setPassword(encoder.encode(dto.getPassword()));

		// Buscar si el correo ya existe
		User userEmail = repository.findByEmail(dto.getEmail());

		// Si existe retornar una excepcion
		if (userEmail != null) {
			throw new UserEmailExistException();
		}

		// Validar que el estado no sea nulo
		if (dto.getState() == null) {
			dto.setState(StateDTO.ACTIVE);
		}

		// Estado inicial del objecto de seguridad del usuario
		dto.setSecurity(securityDTO);

		// Inicializar credenciales

		// Client id
		RandomString randomString = new RandomString(30);
		dto.setClientId(randomString.nextString());

		// Client secret
		randomString = new RandomString(15);
		dto.setClientSecret(randomString.nextString());

		// Crear el usuario
		User user = repository.insert(mapper.map(dto, User.class));

		return mapper.map(user, UserDTO.class);
	}

	@Override
	@Transactional
	public UserDTO update(UserDTO dto) {

		// Si no tiene el id retornar una excepcion
		if (dto.getId() == null) {
			throw new UserNoExistException();
		}

		// Buscar que el usuario exista si no existe retornar una excepcion
		User findUser = repository.findById(dto.getId()).orElseThrow(UserNoExistException::new);

		// El correo no puede ser actualizado
		dto.setEmail(findUser.getEmail());

		// La contraseña no puede ser actualizada
		dto.setPassword(findUser.getPassword());

		// Actualizar el usuario
		User userUpdate = repository.save(mapper.map(dto, User.class));

		return mapper.map(userUpdate, UserDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findById(String id) {

		// Buscar el usuario si no existe retornar una excepcion
		User user = repository.findById(id).orElseThrow(UserNoExistException::new);

		return mapper.map(user, UserDTO.class);

	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO login(String email, String password) {

		// Validar el email
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			throw new UserLoginException();
		}

		// Buscar el usuario
		User user = repository.findByEmail(email.toLowerCase());

		// Si no se encuentra el usuario o esta inactivo retornar una excepcion
		if (user == null || user.getState() == null || user.getState().equals(State.INACTIVE)) {
			throw new UserLoginException();
		}

		// Validar la contraseña
		if (!encoder.matches(password, user.getPassword())) {
			throw new UserLoginException();
		}

		return mapper.map(user, UserDTO.class);
	}

	@Override
	@Transactional
	public UserDTO updatePassword(String id, String newPassword) {

		// Si no tiene el id retornar una excepcion
		if (id == null) {
			throw new UserNoExistException();
		}

		// Buscar que el usuario exista si no existe retornar una excepcion
		User findUser = repository.findById(id).orElseThrow(UserNoExistException::new);

		// Cambiar la contraseña
		findUser.setPassword(encoder.encode(newPassword));

		// Actualizar el usuario
		findUser = repository.save(findUser);

		return mapper.map(findUser, UserDTO.class);

	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO findByEmail(String email) {

		// Buscar que el usuario exista si no existe retornar una excepcion
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new UserNoExistException();
		}

		return mapper.map(user, UserDTO.class);
	}

}
