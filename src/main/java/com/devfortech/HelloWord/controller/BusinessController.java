package com.devfortech.HelloWord.controller;

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

import com.devfortech.HelloWord.dto.BusinessDTO;
import com.devfortech.HelloWord.services.BusinessService;

@RestController
@RequestMapping(value = "/business")
public class BusinessController {

	@Autowired
	private BusinessService service;

	@GetMapping
	public ResponseEntity<Page<BusinessDTO>> findAll(Pageable pageable) {
		Page<BusinessDTO> clients = service.findAll(pageable);
		return ResponseEntity.ok().body(clients);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<BusinessDTO> findById(@PathVariable Long id){
		BusinessDTO client = service.findById(id);
		return ResponseEntity.ok().body(client);
	}

	@PostMapping
	public ResponseEntity<BusinessDTO> create(@RequestBody BusinessDTO dto) {
		dto = service.insert(dto);
		return ResponseEntity.created(null).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BusinessDTO> update(@PathVariable Long id, @RequestBody BusinessDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<BusinessDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
