package com.devfortech.HelloWord.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.entities.Clients;
import com.devfortech.HelloWord.entities.Delivery;
import com.devfortech.HelloWord.entities.Order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @NonNull Long id;
	private @NonNull Long kilometer;
	
	private ClientsDTO client;
	private DeliveryDTO delivery;
	private List<CategoryDTO> categories = new ArrayList<>();
	
	public OrderDTO(Order entity) {
		this.id = entity.getId();
		this.kilometer = entity.getKilometer();
	}
	
	public OrderDTO(Order entity, Clients client, Delivery delivery, Set<Category> categories ) {
		this(entity);
		this.client = new ClientsDTO(client);
		this.delivery = new DeliveryDTO(delivery);
		categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
	}

}
