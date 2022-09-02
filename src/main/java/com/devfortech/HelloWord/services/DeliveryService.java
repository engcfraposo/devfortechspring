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

import com.devfortech.HelloWord.dto.DeliveryDTO;
import com.devfortech.HelloWord.entities.Delivery;
import com.devfortech.HelloWord.repository.DeliveryRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<DeliveryDTO> findAll(Pageable pageable){
		Page<Delivery> clients = repository.findAll(pageable);
		return clients.map(entity -> new DeliveryDTO(entity));

	}
	
	@Transactional(readOnly = true)
	public DeliveryDTO findById(Long id) {
		Optional<Delivery> obj = repository.findById(id);
		Delivery entity = obj.orElseThrow(()-> new ResourceNotFoundException("Business not found!" + id));
		return new DeliveryDTO(entity);
	}

	@Transactional
	public DeliveryDTO insert(DeliveryDTO dto) {
		Delivery entity = new Delivery(dto);
		entity = repository.save(entity);
		return new DeliveryDTO(entity);
	}

	@Transactional
	public DeliveryDTO update(Long id, DeliveryDTO dto) {
		try {
			Delivery entity = repository.getReferenceById(id);
			entity = repository.save(new Delivery(id,dto));
			return new DeliveryDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Business not found!" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Business not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}

}
