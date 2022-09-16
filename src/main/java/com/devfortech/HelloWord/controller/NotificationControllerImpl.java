package com.devfortech.HelloWord.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devfortech.HelloWord.dto.NotificationDTO;
import com.devfortech.HelloWord.services.NotificationService;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationControllerImpl implements NotificationController {

	@Autowired
	private NotificationService service;

	@Override
	@GetMapping
	public ResponseEntity<List<NotificationDTO>> findAll() {
		List<NotificationDTO>list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationDTO> findById(@PathVariable String id){
		NotificationDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@Override
	@PostMapping
	public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
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
	public ResponseEntity<NotificationDTO> update(@PathVariable String id, @RequestBody NotificationDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<NotificationDTO> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
