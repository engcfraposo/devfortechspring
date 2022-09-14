package com.devfortech.HelloWord.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;
import com.devfortech.HelloWord.tests.Factory;

@SpringBootTest
@Transactional
public class CategoryServiceIT {
	private long existingId;
	private long nonExistingId;
	private long countTotalCategories;
	private CategoryDTO dto;
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 999L;
		countTotalCategories = 4L;
		dto = Factory.createCategoryDTO();
	}
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private CategoryRepository repository;
	
	
	@Test
	public void findAllShouldReturnPageWhenPage0Size4() {
		int number = 0;
		int size= 4;
		
		PageRequest pageRequest = PageRequest.of(number,size);
		
		Page<CategoryDTO> result = service.findAll(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(number, result.getNumber());
		Assertions.assertEquals(size, result.getSize());
		Assertions.assertEquals(countTotalCategories, result.getNumberOfElements());
		
	}
	
	@Test
	public void findAllShouldReturnEmptyWhenPageDoesNotExists() {
		int number = 50;
		int size= 10;
		
		PageRequest pageRequest = PageRequest.of(number,size);
		
		Page<CategoryDTO> result = service.findAll(pageRequest);
		
		Assertions.assertTrue(result.isEmpty());
		Assertions.assertEquals(number, result.getNumber());
		Assertions.assertEquals(size, result.getSize());
		Assertions.assertEquals(0, result.getNumberOfElements());
		
	}
	
	@Test
	public void findAllShouldReturnOrderedPageWhenSortByName() {
		int number = 0;
		int size= 4;
		
		PageRequest pageRequest = PageRequest.of(number,size, Sort.by("name"));
		
		Page<CategoryDTO> result = service.findAll(pageRequest);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(number, result.getNumber());
		Assertions.assertEquals(size, result.getSize());
		Assertions.assertEquals(countTotalCategories, result.getNumberOfElements());
		Assertions.assertEquals("Car", result.getContent().get(0).getName());
		Assertions.assertEquals("Fleight", result.getContent().get(1).getName());
		Assertions.assertEquals("Motocycle", result.getContent().get(2).getName());
	}

	@Test
	public void findByIdShouldReturnPageWhenIdExists() {
		CategoryDTO result = service.findById(existingId);
		
		Assertions.assertEquals(existingId, result.getId());
		Assertions.assertEquals("Fleight", result.getName());
	}
	
	@Test
	public void findByIdShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void insertShouldCreateNewCategory() {
		Long newId = countTotalCategories + 1L;
		dto.setId(null);
		CategoryDTO result = service.insert(dto);
		Assertions.assertEquals(newId, result.getId());
	}
	
	@Test
	public void updateShouldModifyTheCategory() {
		dto.setName("Carroça");
		CategoryDTO result = service.update(existingId,dto);
		Assertions.assertEquals(existingId, result.getId());
		Assertions.assertEquals("Carroça", result.getName());
	}
	
	@Test
	public void updateShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
		dto.setName("Carroça");
		dto.setId(nonExistingId);
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId,dto);
		});
	}
	
	@Test
	public void deleteShouldReductOneCategoryWhenIdExists() {
		Long newCountTotalCategories = countTotalCategories - 1L;
		service.delete(existingId);
		Assertions.assertEquals(newCountTotalCategories, repository.count());
	}
	
	@Test
	public void deleteShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
	}
	
}
