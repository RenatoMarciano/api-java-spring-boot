package com.attornatus.api.apijavagerenciarpessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiJavaGerenciarPessoasApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiJavaGerenciarPessoasApplication.class, args);
		System.out.println("Started");
	}

}
