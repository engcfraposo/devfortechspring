package com.devfortech.HelloWord.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.tests.Factory;

@SpringBootTest
@Transactional
public class CategoryRepositoryIT {
	private long existingId;
	private long nonExistingId;
	private long countTotalCategories;
	private Category entity;
	
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 999L;
		countTotalCategories = 4L;
		entity = Factory.createCategoryWithoutData();
	}
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void findAllShouldReturnPageWhenPage0Size4() {
		int number = 0;
		int size= 4;
		
		PageRequest pageRequest = PageRequest.of(number,size);
		
		Page<Category> result = repository.findAll(pageRequest);
		
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
		
		Page<Category> result = repository.findAll(pageRequest);
		
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
		
		Page<Category> result = repository.findAll(pageRequest);
		
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
		Optional<Category> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals(existingId, result.get().getId());
		Assertions.assertEquals("Fleight", result.get().getName());
	}
	
	@Test
	public void findByIdShouldReturnNothingWithIdDoesNotExists() {
		Optional<Category> result = repository.findById(nonExistingId);
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void getReferenceByIdShouldReturnPageWhenIdExists() {
		Category result = repository.getReferenceById(existingId);
		
		Assertions.assertEquals(existingId, result.getId());
		Assertions.assertEquals("Fleight", result.getName());
	}
	
	@Test
	public void getReferenceByIdShouldReturnNothingWithIdDoesNotExists() {
		Optional<Category> result = Optional.of(repository.getReferenceById(nonExistingId));
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals(999, result.get().getId());
	}
	
	@Test
	public void insertShouldCreateNewCategory() {
		entity.setName("Jegue");
		Category result = repository.save(entity);
		Assertions.assertEquals(5L, result.getId());
		Assertions.assertEquals(entity.getName(), result.getName());
	} 
	
	@Test
	public void updateShouldCreateNewCategoryIfIdExists() {
		entity.setName("Jegue");
		entity.setId(existingId);
		Category result = repository.save(entity);
		Assertions.assertEquals(existingId, result.getId());
		Assertions.assertEquals(entity.getName(), result.getName());
	} 
	

	@Test
	public void updateShouldReturnNewCategoryIfIdDoesNotExists() {
		entity.setId(nonExistingId);
		Category result = repository.save(entity);
		Assertions.assertEquals(6L, result.getId());
		Assertions.assertEquals(entity.getName(), result.getName());
	} 
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {;
		repository.deleteById(existingId);
		Optional<Category> result = repository.findById(nonExistingId);
		
		Assertions.assertFalse(result.isPresent());
	} 
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
}