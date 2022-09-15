package com.devfortech.HelloWord.dto;

import org.springframework.data.annotation.Id;

import com.devfortech.HelloWord.entities.Notifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDTO {

	private @Id String notificationId;
	private @NonNull String notification;

	public NotificationsDTO(Notifications entity) {
		this.notificationId = entity.getNotificationId();
		this.notification = entity.getNotification();
	}


}