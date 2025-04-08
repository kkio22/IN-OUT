package com.example.allin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AllInApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllInApplication.class, args);
	}

}
