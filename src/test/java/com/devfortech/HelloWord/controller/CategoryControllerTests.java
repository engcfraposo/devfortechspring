package com.devfortech.HelloWord.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.services.CategoryService;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;
import com.devfortech.HelloWord.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {
	private long existingId;
	private long nonExistingId;
	private long dataViolationId;
	private PageImpl<CategoryDTO> page;
	private CategoryDTO categoryDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		dataViolationId = 666L;
		categoryDTO = Factory.createCategoryDTO();
		page = new PageImpl<CategoryDTO>(List.of(categoryDTO));
		
		//GET /category
		Mockito.when(service.findAll(ArgumentMatchers.any())).thenReturn(page);
		
		//GET /category/id
		Mockito.when(service.findById(existingId)).thenReturn(categoryDTO);
		Mockito.when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		//DELETE /category
		Mockito.doNothing().when(service).delete(existingId);
		Mockito.doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		Mockito.doThrow(DatabaseException.class).when(service).delete(dataViolationId);
	}
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CategoryService service;
	
	@Test
	public void findAllShouldReturnPage() throws Exception {
		ResultActions result = mockMvc.perform(get("/category"));
		
			
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content").isArray());
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.pageable").exists());
		result.andExpect(jsonPath("$.last").exists());
		result.andExpect(jsonPath("$.totalPages").exists());			
	}
	
	@Test
	public void findByIdShouldCategoryWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/category/{id}", existingId));
			
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").exists());
	}
	
	@Test
	public void findByIdShouldThrowNotFoundExceptionWhenIdDoesNotExists() throws Exception {
		ResultActions result = mockMvc.perform(get("/category/{id}", nonExistingId));
		
		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.timestamp").exists());
		result.andExpect(jsonPath("$.status").exists());
		result.andExpect(jsonPath("$.error").exists());
		result.andExpect(jsonPath("$.path").exists());
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/category/{id}", existingId));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void deleteShouldThrowNotFoundExceptionWhenIdDoesNotExists() throws Exception {
		ResultActions result = mockMvc.perform(delete("/category/{id}", nonExistingId));
		
		result.andExpect(status().isNotFound());
		result.andExpect(jsonPath("$.timestamp").exists());
		result.andExpect(jsonPath("$.status").exists());
		result.andExpect(jsonPath("$.error").exists());
		result.andExpect(jsonPath("$.path").exists());
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenhaveViolationId() throws Exception {
		ResultActions result = mockMvc.perform(delete("/category/{id}", dataViolationId));
		
		result.andDo(print());
		result.andExpect(status().isBadRequest());
		result.andExpect(jsonPath("$.timestamp").exists());
		result.andExpect(jsonPath("$.status").exists());
		result.andExpect(jsonPath("$.error").exists());
		result.andExpect(jsonPath("$.path").exists());
	}
}
