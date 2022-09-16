package com.devfortech.HelloWord.dto;

import javax.persistence.Id;

import com.devfortech.HelloWord.entities.Notification;

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
public class NotificationDTO {

	
	private @Id String id;
	private @NonNull String notification;

	public NotificationDTO(Notification entity) {
		this.setId(entity.getId());
		this.setNotification(entity.getNotification());
	}
}