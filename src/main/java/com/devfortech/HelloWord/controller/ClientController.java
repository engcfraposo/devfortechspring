package com.devfortech.HelloWord.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devfortech.HelloWord.dto.ClientsDTO;
import com.devfortech.HelloWord.services.ClientsService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientsService service;
	
	@GetMapping
	public ResponseEntity<ArrayList<ClientsDTO>> findAll() {
		ArrayList<ClientsDTO> clients = service.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClientsDTO> findById(@PathVariable Long id) {
		ClientsDTO client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<ClientsDTO> create(@RequestBody ClientsDTO dto){
		dto = service.insert(dto);
		return ResponseEntity.created(null).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientsDTO> update(@PathVariable Long id, @RequestBody ClientsDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<ClientsDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
