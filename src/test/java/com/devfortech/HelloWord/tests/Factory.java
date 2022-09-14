package com.devfortech.HelloWord.tests;

import com.devfortech.HelloWord.dto.CategoryDTO;
import com.devfortech.HelloWord.dto.DeliveryDTO;
import com.devfortech.HelloWord.entities.Category;
import com.devfortech.HelloWord.entities.Delivery;

public class Factory {
	public static Category createCategory() {
		return new Category(1L, "Fleight"); 
	}
	public static Category createCategoryWithoutData() {
		return new Category();
	}
	public static CategoryDTO createCategoryDTO() {
		Category category = createCategory();
		return new CategoryDTO(category);
	}
	public static Delivery createDelivery() {
		return new Delivery(1L, "Carreta Furacão", "0000", "carreta@furacao.com", "Rua Nenhuma", "50000-000", "recife", "pernambuco", 100L );
	}
	public static DeliveryDTO createDeliveryDTO() {
		Delivery delivery = createDelivery();
		return new DeliveryDTO(delivery);
	}
}
