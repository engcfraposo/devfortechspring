package com.devfortech.HelloWord.dto;

import java.io.Serializable;
import java.util.Objects;

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
	
	public ClientsDTO() {}
	
	public ClientsDTO(Long id, String name, String cpf, String email, String endereco, String cep, String city,
			String country) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.endereco = endereco;
		this.cep = cep;
		this.city = city;
		this.country = country;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, city, country, cpf, email, endereco, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientsDTO other = (ClientsDTO) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(email, other.email) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
