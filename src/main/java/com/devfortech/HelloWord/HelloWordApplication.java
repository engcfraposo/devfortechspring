package com.devfortech.HelloWord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@RestController
public class HelloWordApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWordApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Word") String name) {
		return String.format("Hello %s!", name);
	}

}
