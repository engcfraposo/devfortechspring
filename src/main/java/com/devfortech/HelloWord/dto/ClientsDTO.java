package com.devfortech.HelloWord.dto;

import java.io.Serializable;

import com.devfortech.HelloWord.entities.Clients;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ClientsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cpf;
	private String email;
	private String endereco;
	private String cep;
	private String city;
	private String country;
	
	public ClientsDTO(Clients entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.email = entity.getEmail();
		this.endereco = entity.getEndereco();
		this.cep = entity.getCep();
		this.city = entity.getCity();
		this.country = entity.getCountry();
	}

}
