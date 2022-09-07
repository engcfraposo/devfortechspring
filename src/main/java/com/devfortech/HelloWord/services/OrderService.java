package com.devfortech.HelloWord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.dto.OrderDTO;
import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.entities.Clients;
import com.devfortech.HelloWord.entities.Delivery;
import com.devfortech.HelloWord.entities.Order;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.repository.ClientsRepository;
import com.devfortech.HelloWord.repository.DeliveryRepository;
import com.devfortech.HelloWord.repository.OrderRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ClientsRepository clientRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<OrderDTO> findAll(Pageable pageable){
		Page<Order> clients = repository.findAll(pageable);
		return clients.map(entity -> new OrderDTO(entity, entity.getClient(), entity.getDelivery(), entity.getCategories()));
	}
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(()-> new ResourceNotFoundException("Client not found!" + id));
		return new OrderDTO(entity, entity.getClient(), entity.getDelivery(), entity.getCategories());
	}

	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order entity = new Order(dto);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new OrderDTO(entity, entity.getClient(), entity.getDelivery(), entity.getCategories());
	}

	@Transactional
	public OrderDTO update(Long id, OrderDTO dto) {
		try {
			Order entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			return new OrderDTO(entity, entity.getClient(), entity.getDelivery(), entity.getCategories());
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Order not found!" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Order not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}
	
	private void copyDtoToEntity(OrderDTO dto, Order entity) {
		Clients client = clientRepository.getReferenceById(dto.getClient().getId());
		entity.setClient(client);
		Delivery delivery = deliveryRepository.getReferenceById(dto.getDelivery().getId());
		entity.setDelivery(delivery);
		
		entity.getCategories().clear();
		for (CategoryDTO categoryDto: dto.getCategories()) {
			Category category = categoryRepository.getReferenceById(categoryDto.getId());
			entity.getCategories().add(category);
		}
	}

}
