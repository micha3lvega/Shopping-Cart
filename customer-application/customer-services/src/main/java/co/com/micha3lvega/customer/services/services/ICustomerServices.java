package co.com.micha3lvega.customer.services.services;

import java.util.List;

import co.com.micha3lvega.customer.commons.dto.CustomerDTO;

public interface ICustomerServices {

	List<CustomerDTO> findAll();

	CustomerDTO findByID(String id);
	
	CustomerDTO create(CustomerDTO dto);
	
	CustomerDTO update(CustomerDTO dto);
	
}
