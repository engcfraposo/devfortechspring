package com.devfortech.HelloWord.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devfortech.HelloWord.dto.ClientsDTO;
import com.devfortech.HelloWord.dto.DeliveryDTO;
import com.devfortech.HelloWord.dto.OrderDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_orders")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @NonNull Long id;
	private @NonNull Long kilometer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	private Clients client;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "delivery_id", nullable = false)
	private Delivery delivery;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@CreationTimestamp
	private Instant created_at;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@UpdateTimestamp
	private Instant updated_at;
	
	public Order(OrderDTO dto, Clients client, Delivery delivery) {
		this.id = dto.getId();
		this.kilometer = dto.getKilometer();
		this.client = client;
		this.delivery = delivery;
	}
	
	public Order(OrderDTO dto) {
		this.id = dto.getId();
		this.kilometer = dto.getKilometer();
	}
}
