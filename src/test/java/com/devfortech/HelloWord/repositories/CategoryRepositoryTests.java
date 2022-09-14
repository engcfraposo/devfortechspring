package com.devfortech.HelloWord.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.tests.Factory;

@DataJpaTest
public class CategoryRepositoryTests {
	private long existingId;
	private long nonExistingId;
	
	@Autowired
	private CategoryRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
	}
	
	@Test
	public void saveShouldFindOptionalObjectWhenIdExists() {
		Category category = repository.save(Factory.createCategory());
		
		assertThat(category).hasFieldOrPropertyWithValue("name", "Fleight");
	}
	
	@Test
	public void findByIdShouldFindOptionalObjectWhenIdExists() {
		repository.save(Factory.createCategory());
		Optional<Category> category = repository.findById(existingId);
		
		Assertions.assertTrue(category.isPresent());
	}
	
	@Test
	public void findByIdShouldFindOptionalObjectWhenIdInvalid() {
		Optional<Category> category = repository.findById(nonExistingId);
		
		Assertions.assertFalse(category.isPresent());
	}
	
	@Test
	public void saveShouldAlterDataIdExists() {

		Category category = repository.save(Factory.createCategory());
			
		category.setName("Train");
		
		category = repository.save(category);
		
		assertThat(category).hasFieldOrPropertyWithValue("id", 4L);
		assertThat(category).hasFieldOrPropertyWithValue("name", "Train");
		
	}
	
	
	@Test
	public void getReferenceById() {
		repository.save(Factory.createCategory());
		
		Category category = repository.getReferenceById(4L);
		
		assertThat(category).hasFieldOrPropertyWithValue("id", 4L);
	}
	
}
