package com.cultisoft.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cultisoft.repositories")
@EntityScan("com.cultisoft.entities")
public class CultiSoftPdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CultiSoftPdsApplication.class, args);
	}

}
