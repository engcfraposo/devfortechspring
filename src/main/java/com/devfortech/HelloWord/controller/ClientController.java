package com.devfortech.HelloWord.controller;

import org.springframework.web.bind.annotation.RestController;

import com.devfortech.HelloWord.dto.ClientsDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@GetMapping(value = "/findall")
	public ClientsDTO findAll() {
		ClientsDTO clients = new ClientsDTO(1L, "claudio", "097097777l12", "teste@teste.com", "rua teste", "teste", "teste", "teste");
		return clients;
	}
}
