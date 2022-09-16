package com.devfortech.HelloWord.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.CategoryDTO;


public interface CategoryController {
	public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable); 
	public ResponseEntity<CategoryDTO> findById(Long id);
	public ResponseEntity<CategoryDTO> create(CategoryDTO dto);
	public ResponseEntity<CategoryDTO> update(Long id,CategoryDTO dto);
	public ResponseEntity<CategoryDTO> delete(Long id);
}
