package com.devfortech.HelloWord.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devfortech.HelloWord.dto.DeliveryDTO;
import com.devfortech.HelloWord.entities.Delivery;
import com.devfortech.HelloWord.repository.DeliveryRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;
import com.devfortech.HelloWord.tests.Factory;

@ExtendWith(SpringExtension.class)
public class DeliveryServiceTests {
	private long existingId;
	private long nonExistingId;
	private long dataViolationId;
	private PageImpl<Delivery> page;
	private Delivery delivery;
	private DeliveryDTO deliveryDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		dataViolationId = 666L;
		delivery = Factory.createDelivery();
		deliveryDTO = Factory.createDeliveryDTO();
		page = new PageImpl<Delivery>(List.of(delivery));
		
		//findAll
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		//findById
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(delivery));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		//insert and update
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(delivery);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(delivery);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		//delete
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dataViolationId);
	}
	
	@InjectMocks
	private DeliveryService service;
	
	@Mock
	private DeliveryRepository repository;
	
	@Test
	public void findAllShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<DeliveryDTO> result = service.findAll(pageable);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldReturnDeliveryWhenIdExists() {
		DeliveryDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findById(existingId);
	}
	
	@Test
	public void findByIdShoudThrowResouceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
				service.findById(nonExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
	}
	
	@Test
	public void insertShouldReturnDeliveryDTOWhenSaveData() {
		DeliveryDTO result = service.insert(deliveryDTO);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnDeliveryDTOWhenIDExists() {
		DeliveryDTO result = service.update(existingId, deliveryDTO);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhemIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, deliveryDTO);
		});
		Mockito.verify(repository, Mockito.times(1)).getReferenceById(nonExistingId);
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(()-> {
			service.delete(existingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowResouceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenUseDataViolationId() {
		Assertions.assertThrows(DatabaseException.class, () -> {
			service.delete(dataViolationId);
		});
		Mockito.verify(repository, Mockito.times(1)).deleteById(dataViolationId);
	}
	
}
