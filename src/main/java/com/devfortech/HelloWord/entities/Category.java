package com.devfortech.HelloWord.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devfortech.HelloWord.dto.CategoryDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private @NonNull Long id;
	private @NonNull String name;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@CreationTimestamp
	private Instant created_at;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@UpdateTimestamp
	private Instant updated_at;

	public Category(CategoryDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
	}

	public Category(Long id, CategoryDTO dto) {
		this.id = id;
		this.name = dto.getName();
	}

}