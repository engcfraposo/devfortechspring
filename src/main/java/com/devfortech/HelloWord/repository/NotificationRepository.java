package com.devfortech.HelloWord.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.devfortech.HelloWord.entities.Notifications;

@Repository
public interface NotificationRepository extends MongoRepository<Notifications, String> {
}