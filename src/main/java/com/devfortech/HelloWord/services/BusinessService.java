package com.devfortech.HelloWord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.BusinessDTO;
import com.devfortech.HelloWord.entities.Business;
import com.devfortech.HelloWord.repository.BusinessRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class BusinessService {
	
	@Autowired
	private BusinessRepository repository;
	
	@Transactional(readOnly = true)
	public Page<BusinessDTO> findAll(Pageable pageable){
		Page<Business> clients = repository.findAll(pageable);
		return clients.map(entity -> new BusinessDTO(entity));

	}
	
	@Transactional(readOnly = true)
	public BusinessDTO findById(Long id) {
		Optional<Business> obj = repository.findById(id);
		Business entity = obj.orElseThrow(()-> new ResourceNotFoundException("Business not found!" + id));
		return new BusinessDTO(entity);
	}

	@Transactional
	public BusinessDTO insert(BusinessDTO dto) {
		Business entity = new Business(dto);
		entity = repository.save(entity);
		return new BusinessDTO(entity);
	}

	@Transactional
	public BusinessDTO update(Long id, BusinessDTO dto) {
		try {
			Business entity = repository.getReferenceById(id);
			entity = repository.save(new Business(id,dto));
			return new BusinessDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Business not found!" + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Business not found!" + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Ïntegrity violation");
		}
	}

}
