package com.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiProjectApplication.class, args);
	}

}
