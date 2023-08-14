package com.anamika.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.anamika")
@EntityScan(basePackages = "com.anamika")
@EnableJpaRepositories(basePackages = "com.anamika")
public class TransactionSpringbootProjectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TransactionSpringbootProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("heloooo");
	}
	
	

}
