package com.devfortech.HelloWord.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.devfortech.HelloWord.dto.NotificationDTO;

public interface NotificationController {
	public ResponseEntity<List<NotificationDTO>> findAll();
	public ResponseEntity<NotificationDTO> findById(String id);
	public ResponseEntity<NotificationDTO> create(NotificationDTO dto); 
	public ResponseEntity<NotificationDTO> update(String id,NotificationDTO dto);
	public ResponseEntity<NotificationDTO> delete(String id);
}
