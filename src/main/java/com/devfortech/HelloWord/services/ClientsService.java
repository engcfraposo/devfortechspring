package com.devfortech.HelloWord.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.ClientsDTO;
import com.devfortech.HelloWord.entities.Clients;
import com.devfortech.HelloWord.repository.ClientsRepository;
import com.devfortech.HelloWord.services.exceptions.DatabaseException;
import com.devfortech.HelloWord.services.exceptions.ResourceNotFoundException;

@Service
public class ClientsService implements UserDetailsService {
	
	@Autowired
	private ClientsRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientsDTO> findAll(Pageable pageable){
		Page<Clients> clients = repository.findAll(pageable);
		return clients.map(entity -> new ClientsDTO(entity));

	}
	
	@Transactional(readOnly = true)
	public ClientsDTO findById(Long id) {
		Optional<Clients> obj = repository.findById(id);
		Clients entity = obj.orElseThrow(()-> new ResourceNotFoundException("Client not found!" + id));
		return new ClientsDTO(entity);
	}
	
	@Transactional
	public ClientsDTO insert(ClientsDTO dto) {
		Clients entity = new Clients(dto);
		entity = repository.save(entity);
		return new ClientsDTO(entity);
	}

	@Transactional
	public ClientsDTO update(Long id, ClientsDTO dto) {
		try {
			Clients entity = repository.getReferenceById(id);
			entity = repository.save(new Clients(id,dto));
			return new ClientsDTO(entity);
		}
		catch (EntityNotFoundException e) {
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Clients client = repository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found!" + username));
		return client;
	}

}
