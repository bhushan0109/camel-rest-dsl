package com.javatechie.spring.camel.api;

import org.apache.camel.component.http.HttpComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelRestDslApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelRestDslApplication.class, args);
	}

	@Bean({ "http", "https" })
	HttpComponent httpComponent() {
		return new HttpComponent();
	}

}
