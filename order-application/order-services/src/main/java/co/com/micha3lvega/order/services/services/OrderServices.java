package co.com.micha3lvega.order.services.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.micha3lvega.order.commons.dto.OrderDTO;
import co.com.micha3lvega.order.services.exception.OrderNotExitsException;
import co.com.micha3lvega.order.services.model.Order;
import co.com.micha3lvega.order.services.repository.OrderRepository;

public class OrderServices implements IOrderServices {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<OrderDTO> findAll() {
		return repository.findAll().stream().map(order -> {
			return mapper.map(order, OrderDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	public OrderDTO findByID(String id) {

		// Buscar la orden si no existe retornar una excepcion
		Order order = repository.findById(id).orElseThrow(OrderNotExitsException::new);

		return mapper.map(order, OrderDTO.class);

	}

	@Override
	public OrderDTO create(OrderDTO dto) {

		// Crear la orden
		Order order = repository.insert(mapper.map(dto, Order.class));

		return mapper.map(order, OrderDTO.class);

	}

	@Override
	public OrderDTO update(OrderDTO dto) {
		
		// Actualizar la orden
		Order order = repository.save(mapper.map(dto, Order.class));

		return mapper.map(order, OrderDTO.class);
		
	}

}
