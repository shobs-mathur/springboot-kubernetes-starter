package com.shobs.springboot.springenvers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringEnversApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEnversApplication.class, args);
	}

}
