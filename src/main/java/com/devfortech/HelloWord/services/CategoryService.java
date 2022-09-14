package com.devfortech.HelloWord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.repository.CategoryRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAll(Pageable pageable){
		Page<Category> category = repository.findAll(pageable);
		return category.map(entity -> new CategoryDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(()-> new ResourceNotFoundException("Client not found!" + id));
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category(dto);
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(id);
			entity = repository.save(new Category(id,dto));
			return new CategoryDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Client not found!" + id);
		}
		catch (JpaObjectRetrievalFailureException e) {
			throw new ResourceNotFoundException("Client not found!" + id);
		}
	}

	public void delete(Long id) {
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

}
