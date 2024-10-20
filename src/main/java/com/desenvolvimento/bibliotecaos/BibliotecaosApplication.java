package com.desenvolvimento.bibliotecaos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.desenvolvimento")
@EntityScan(basePackages ={ "com.desenvolvimento.domains", "com.desenvolvimento.domains.enums"})
@EnableJpaRepositories(basePackages = "com.desenvolvimento.repositories")
@SpringBootApplication
public class BibliotecaosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaosApplication.class, args);
	}

}
