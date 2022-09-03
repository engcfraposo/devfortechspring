package com.devfortech.HelloWord.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devfortech.HelloWord.dto.DeliveryDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_delivery")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Delivery implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @NonNull Long id;
	private @NonNull String name;
	private @NonNull String cnpj;
	private @NonNull String email;
	private @NonNull String endereco;
	private @NonNull String cep;
	private @NonNull String city;
	private @NonNull String country;
	private @NonNull Long price;
	
	@OneToMany(mappedBy = "delivery")
	private Set<Order> orders_id;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@CreationTimestamp
	private Instant created_at;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@UpdateTimestamp
	private Instant updated_at;

	public Delivery(DeliveryDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.cnpj = dto.getCnpj();
		this.email = dto.getEmail();
		this.endereco = dto.getEndereco();
		this.cep = dto.getCep();
		this.city = dto.getCity();
		this.country = dto.getCountry();
		this.price = dto.getPrice();
	}

	public Delivery(Long id, DeliveryDTO dto) {
		this.id = id;
		this.name = dto.getName();
		this.cnpj = dto.getCnpj();
		this.email = dto.getEmail();
		this.endereco = dto.getEndereco();
		this.cep = dto.getCep();
		this.city = dto.getCity();
		this.country = dto.getCountry();
		this.price = dto.getPrice();
	}

}