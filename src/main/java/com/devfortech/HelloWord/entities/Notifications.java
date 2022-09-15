package com.devfortech.HelloWord.entities;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devfortech.HelloWord.dto.NotificationsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
	
	private @Id String notificationId;
	private @NonNull String notification;
	private @CreationTimestamp Instant created_at;
	private @UpdateTimestamp Instant updated_at;
	
	public Notifications(String id, NotificationsDTO dto) {
		this.setNotificationId(id);
		this.setNotification(dto.getNotification());
	}
}
