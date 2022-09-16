package com.devfortech.HelloWord.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.DeliveryDTO;


public interface DeliveryController {
	public ResponseEntity<Page<DeliveryDTO>> findAll(Pageable pageable);
	public ResponseEntity<DeliveryDTO> findById(Long id);
	public ResponseEntity<DeliveryDTO> create(DeliveryDTO dto);
	public ResponseEntity<DeliveryDTO> update(Long id, DeliveryDTO dto);
	public ResponseEntity<DeliveryDTO> delete(Long id);
}
