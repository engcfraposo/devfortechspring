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

import com.devfortech.HelloWord.dto.DeliveryDTO;
import com.devfortech.HelloWord.services.DeliveryService;

@RestController
@RequestMapping(value = "/business")
public class DeliveryControllerImpl implements DeliveryController {

	@Autowired
	private DeliveryService service;

	@Override
	@GetMapping
	public ResponseEntity<Page<DeliveryDTO>> findAll(Pageable pageable) {
		Page<DeliveryDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DeliveryDTO> findById(@PathVariable Long id){
		DeliveryDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@Override
	@PostMapping
	public ResponseEntity<DeliveryDTO> create(@RequestBody DeliveryDTO dto) {
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
	public ResponseEntity<DeliveryDTO> update(@PathVariable Long id, @RequestBody DeliveryDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DeliveryDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
