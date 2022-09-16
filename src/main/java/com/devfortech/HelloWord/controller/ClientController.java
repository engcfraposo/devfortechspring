package com.devfortech.HelloWord.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.ClientsDTO;


public interface ClientController {
	public ResponseEntity<Page<ClientsDTO>> findAll(Pageable pageable); 
	public ResponseEntity<ClientsDTO> findById(Long id);
	public ResponseEntity<ClientsDTO> create(ClientsDTO dto);
	public ResponseEntity<ClientsDTO> update(Long id,ClientsDTO dto);
	public ResponseEntity<ClientsDTO> delete(Long id);
}
