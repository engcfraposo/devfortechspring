package com.devfortech.HelloWord.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devfortech.HelloWord.dto.ClientsDTO;
import com.devfortech.HelloWord.services.ClientsService;

@RestController
@RequestMapping(value = "/category")
public class ClientControllerImpl implements ClientController {

	@Autowired private ClientsService service;

	@Override
	@GetMapping
	public ResponseEntity<Page<ClientsDTO>> findAll(Pageable pageable) {
		Page<ClientsDTO> clients = service.findAll(pageable);
		return ResponseEntity.ok().body(clients);
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientsDTO> findById(@PathVariable Long id){
		ClientsDTO client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}

	@Override
	@PostMapping
	public ResponseEntity<ClientsDTO> create(@RequestBody ClientsDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(dto.getId())
					.toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@Override
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientsDTO> update(@PathVariable Long id, @RequestBody ClientsDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientsDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
