package co.com.micha3lvega.order.services.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.micha3lvega.order.commons.dto.OrderDTO;
import co.com.micha3lvega.order.services.exception.OrderNotExitsException;
import co.com.micha3lvega.order.services.model.Order;
import co.com.micha3lvega.order.services.repository.OrderRepository;
import co.com.micha3lvega.order.services.services.IOrderServices;

@Service
public class OrderServices implements IOrderServices {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		return repository.findAll().stream().map(order -> {
			return mapper.map(order, OrderDTO.class);
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public OrderDTO findByID(String id) {

		// Buscar la orden si no existe retornar una excepcion
		Order order = repository.findById(id).orElseThrow(OrderNotExitsException::new);

		return mapper.map(order, OrderDTO.class);

	}

	@Override
	@Transactional
	public OrderDTO create(OrderDTO dto) {

		// Crear la orden
		Order order = repository.insert(mapper.map(dto, Order.class));

		return mapper.map(order, OrderDTO.class);

	}

	@Override
	@Transactional
	public OrderDTO update(OrderDTO dto) {

		// Actualizar la orden
		Order order = repository.save(mapper.map(dto, Order.class));

		return mapper.map(order, OrderDTO.class);

	}

}
