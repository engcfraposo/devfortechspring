package com.devfortech.HelloWord.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devfortech.HelloWord.dto.NotificationsDTO;
import com.devfortech.HelloWord.entities.Notifications;
import com.devfortech.HelloWord.repository.NotificationRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class NotificationService {
	
	@Autowired()
	private NotificationRepository repository;
	

	public List<NotificationsDTO> findAll(){
		List<Notifications> category = repository.findAll();
		return category.stream().map(entity -> new NotificationsDTO(entity)).toList();
	}
	

	public NotificationsDTO findById(String id) {
		Optional<Notifications> obj = repository.findById(id);
		Notifications entity = obj
				.orElseThrow(()-> new ResourceNotFoundException("Notification not found!" + id));
		return new NotificationsDTO(entity);
	}
	

	public NotificationsDTO insert(NotificationsDTO dto) {
		Notifications entity = new Notifications();
		copyDtoToEntity(dto,entity);
		entity = repository.save(entity);
		return new NotificationsDTO(entity);
	}


	public NotificationsDTO update(String id, NotificationsDTO dto) {
		Optional<Notifications> obj = repository.findById(id);
		obj.orElseThrow(()-> new ResourceNotFoundException("Notification not found!" + id));
		Notifications entity = obj.get();
		copyDtoToEntity(dto,entity);
		entity.setNotificationId(id);
		entity = repository.save(entity);
		return new NotificationsDTO(entity);
	}

	public void delete(String id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Notification not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}
	
	private void copyDtoToEntity(NotificationsDTO dto, Notifications entity) {
		entity.setNotificationId(dto.getNotificationId());
		entity.setNotification(dto.getNotification());
	}
	
}
