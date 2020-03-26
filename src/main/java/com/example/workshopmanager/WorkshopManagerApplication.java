package com.example.workshopmanager;

import com.example.workshopmanager.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WorkshopManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopManagerApplication.class, args);
	}

}
