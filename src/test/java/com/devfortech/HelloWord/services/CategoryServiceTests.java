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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {
	private long existingId;
	private long nonExistingId;
	private PageImpl<Category> page;
	private Category category;
	private CategoryDTO categoryDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		category = new Category(1L, "Fleight");
		categoryDTO = new CategoryDTO(1L, "Fleight");
		page = new PageImpl<Category>(List.of(category));
		
		//findAll
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);
		
		//findById
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(category));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		//insert and update
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(category);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(category);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
	}
	
	@InjectMocks
	private CategoryService service;
	
	@Mock
	private CategoryRepository repository;
	
	@Test
	public void findAllShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		Page<CategoryDTO> result = service.findAll(pageable);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldReturnCategoryWhenIdExists() {
		CategoryDTO result = service.findById(existingId);
		
		Assertions.assertNotNull(result);
		Mockito.verify(repository, Mockito.times(1)).findById(existingId);
	}
	
	@Test
	public void findByIdShoudThrowResouceNotFoundExceptionWhenIdDoesExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
				service.findById(nonExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
	}
	
	@Test
	public void insertShouldReturnCategoryDTOWhenSaveData() {
		CategoryDTO result = service.insert(categoryDTO);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldReturnCategoryDTOWhenIDExists() {
		CategoryDTO result = service.update(existingId, categoryDTO);
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhemIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, categoryDTO);
		});
		Mockito.verify(repository, Mockito.times(1)).getReferenceById(nonExistingId);
	}
}
