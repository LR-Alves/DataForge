package com.dataforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(info = @Info(title = "DataForge" , version = "0.1", description = "DataForge -aplicacao de gerador de dados"))
@SpringBootApplication
public class DataForgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataForgeApplication.class, args);
	}

}
