package co.com.micha3lvega.order.services.services;

import java.util.List;

import co.com.micha3lvega.order.commons.dto.OrderDTO;

public interface IOrderServices {
	
	List<OrderDTO> findAll();
	
	OrderDTO findByID(String id);
	
	OrderDTO create(OrderDTO dto);
	
	OrderDTO update(OrderDTO dto);

}
