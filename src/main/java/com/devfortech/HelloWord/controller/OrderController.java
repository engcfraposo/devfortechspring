package com.devfortech.HelloWord.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.OrderDTO;

public interface OrderController {
	public ResponseEntity<Page<OrderDTO>> findAll(Pageable pageable);
	public ResponseEntity<OrderDTO> findById(Long id);
	public ResponseEntity<OrderDTO> create(OrderDTO dto);
	public ResponseEntity<OrderDTO> update(Long id,OrderDTO dto);
	public ResponseEntity<OrderDTO> delete(Long id);
}
