package com.devfortech.HelloWord.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.devfortech.HelloWord.dto.ClientsDTO;

@Service
public class ClientsService {
	
	public ArrayList<ClientsDTO> findAll(){
		ArrayList<ClientsDTO> clients = new ArrayList<ClientsDTO>();
		clients.add(new ClientsDTO(1L, "claudio", "097097777l12", "teste@teste.com", "rua teste", "teste", "teste", "teste")); 
		clients.add(new ClientsDTO(2L, "josé", "097097777l13", "teste@teste.com", "rua teste", "teste", "teste", "teste")); 
		return clients;
	}
	
	public ClientsDTO findById(Long id) {
		ClientsDTO client = new ClientsDTO(id, "claudio", "097097777l12", "teste@teste.com", "rua teste", "teste", "teste", "teste");
		return client;
	}

	public ClientsDTO insert(ClientsDTO dto) {
		ClientsDTO client = new ClientsDTO(1L, dto.getName(), dto.getCpf(), "teste@teste.com", "rua teste", "teste", "teste", "teste");
		return client;
	}

	public ClientsDTO update(Long id, ClientsDTO dto) {
		ClientsDTO client = new ClientsDTO(id, dto.getName(), dto.getCpf(), "teste@teste.com", "rua teste", "teste", "teste", "teste");
		return client;
	}

	public void delete(Long id) {

	}
	
}
