package com.devfortech.HelloWord.dto;

import java.io.Serializable;

import com.devfortech.HelloWord.entities.Business;

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
public class BusinessDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String cnpj;
	private String email;
	private String endereco;
	private String cep;
	private String city;
	private String country;
	private Long price;
	
	public BusinessDTO(Business entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.cnpj = entity.getCnpj();
		this.email = entity.getEmail();
		this.endereco = entity.getEndereco();
		this.cep = entity.getCep();
		this.city = entity.getCity();
		this.country = entity.getCountry();
		this.price = entity.getPrice();
	}

}
