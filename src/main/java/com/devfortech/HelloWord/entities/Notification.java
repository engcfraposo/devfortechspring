package com.devfortech.HelloWord.entities;

import java.time.Instant;

import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devfortech.HelloWord.dto.CategoryDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Notification {

	private @Id String id;
	private @NonNull String notification;
	private @CreationTimestamp Instant created_at;
	private @UpdateTimestamp Instant updated_at;

	public Notification(String id, CategoryDTO dto) {
		this.id = id;
		this.notification = dto.getName();
	}

}