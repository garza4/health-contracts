package com.health.contracts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


@SpringBootApplication
public class ContractsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractsApplication.class, args);
	}

}
