package com.devfortech.HelloWord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.devfortech.HelloWord.repository.NotificationRepository;


@SpringBootApplication()
@EnableMongoRepositories(basePackageClasses = NotificationRepository.class)
public class HelloWordApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWordApplication.class, args);
	}
}
