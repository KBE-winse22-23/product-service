package com.onlineshop.productmicroservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Product Microservice", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:8080"), @Server(url = "http://example.com")},
		tags = {@Tag(name = "Product", description = "This is Product Microservice Controller End Points ")}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
