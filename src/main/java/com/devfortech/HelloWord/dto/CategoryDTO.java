package com.devfortech.HelloWord.dto;

import java.io.Serializable;

import com.devfortech.HelloWord.entities.Category;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private @NonNull String name;

	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

}