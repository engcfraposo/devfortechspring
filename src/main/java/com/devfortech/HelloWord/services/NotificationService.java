package com.devfortech.HelloWord.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devfortech.HelloWord.dto.NotificationDTO;
import com.devfortech.HelloWord.entities.Notification;
import com.devfortech.HelloWord.repository.NotificationRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository repository;
	

	public List<NotificationDTO> findAll(){
		List<Notification> list = repository.findAll();
		return list
				.stream()
				.map(entity -> new NotificationDTO(entity))
				.toList();
	}
	

	public NotificationDTO findById(String id) {
		Optional<Notification> obj = repository.findById(id);
		Notification entity = obj.orElseThrow(()-> new ResourceNotFoundException("Client not found!" + id));
		return new NotificationDTO(entity);
	}
	

	public NotificationDTO insert(NotificationDTO dto) {
		Notification entity = new Notification();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new NotificationDTO(entity);
	}

	public NotificationDTO update(String id, NotificationDTO dto) {
			Optional<Notification> obj = repository.findById(id);
			obj.orElseThrow(()-> new ResourceNotFoundException("Client not found!" + id));
			Notification entity = obj.get();
			copyDtoToEntity(dto,entity);
			entity.setId(id);
			entity = repository.save(entity);
			return new NotificationDTO(entity);
	}

	public void delete(String id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Client not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}
	
	private void copyDtoToEntity(NotificationDTO dto, Notification entity) {
		entity.setId(dto.getId());
		entity.setNotification(dto.getNotification());
	}
}
