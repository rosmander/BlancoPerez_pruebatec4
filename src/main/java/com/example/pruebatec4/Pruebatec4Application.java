package com.example.pruebatec4;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Pruebatec4Application {

	public static void main(String[] args) {
		SpringApplication.run(Pruebatec4Application.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API de Agencia de Turismo")
				.version("1.0")
				.description("API para la gestión de reservas de hoteles y vuelos."));
	}
}