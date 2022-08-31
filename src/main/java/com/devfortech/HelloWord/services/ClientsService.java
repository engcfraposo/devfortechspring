package com.devfortech.HelloWord.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfortech.HelloWord.dto.ClientsDTO;
import com.devfortech.HelloWord.entities.Clients;
import com.devfortech.HelloWord.repository.ClientsRepository;

@Service
public class ClientsService {
	
	@Autowired
	private ClientsRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientsDTO> findAll(){
		List<Clients> clients = repository.findAll();
		
		return clients
				.stream()
				.map(entity -> new ClientsDTO(entity))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientsDTO findById(Long id) throws Exception {
		Optional<Clients> obj = repository.findById(id);
		Clients entity = obj.orElseThrow(()-> new Exception());
		return new ClientsDTO(entity);
	}

	@Transactional
	public ClientsDTO insert(ClientsDTO dto) {
		Clients entity = new Clients(dto);
		entity = repository.save(entity);
		return new ClientsDTO(entity);
	}

	@Transactional
	public ClientsDTO update(Long id, ClientsDTO dto) throws Exception {
		try {
			Clients entity = repository.getReferenceById(id);
			entity = repository.save(new Clients(id,dto));
			return new ClientsDTO(entity);
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
