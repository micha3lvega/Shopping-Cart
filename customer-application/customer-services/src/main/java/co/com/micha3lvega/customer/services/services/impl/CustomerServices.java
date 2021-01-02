package co.com.micha3lvega.customer.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;
import co.com.micha3lvega.customer.services.exception.CustomerInvalidInformacionException;
import co.com.micha3lvega.customer.services.exception.CustomerNotExistException;
import co.com.micha3lvega.customer.services.model.Customer;
import co.com.micha3lvega.customer.services.repository.CustomerRepository;
import co.com.micha3lvega.customer.services.rest.client.UserRestClient;
import co.com.micha3lvega.customer.services.services.ICustomerServices;
import co.com.micha3lvega.product.commons.dto.UserDTO;

@Service
public class CustomerServices implements ICustomerServices {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private UserRestClient userClient;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<CustomerDTO> findAll() {
		return repository.findAll().stream().map(customer -> {
			return mapper.map(customer, CustomerDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CustomerDTO findByID(String id) {

		// Buscar el cliente si no existe retornar una excepcion
		Customer customer = repository.findById(id).orElseThrow(CustomerNotExistException::new);
		return mapper.map(customer, CustomerDTO.class);

	}

	@Override
	@Transactional
	public CustomerDTO create(CustomerDTO dto) {

		// Validar la informacion del usuario
		if (dto == null || dto.getUser() == null) {
			throw new CustomerInvalidInformacionException();
		}

		// Crear el usuario
		UserDTO user = userClient.create(dto.getUser());
		dto.setUser(user);

		// Crear el cliente
		Customer customer = repository.insert(mapper.map(dto, Customer.class));

		return mapper.map(customer, CustomerDTO.class);
	}

	@Override
	@Transactional
	public CustomerDTO update(CustomerDTO dto) {

		// Actualizar la informacion del usuario
		UserDTO user = userClient.update(dto.getUser());
		dto.setUser(user);

		// Actualizar cliente
		Customer customer = repository.save(mapper.map(dto, Customer.class));

		return mapper.map(customer, CustomerDTO.class);

	}

}
