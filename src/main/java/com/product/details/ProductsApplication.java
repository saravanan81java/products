package com.product.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}
	
	@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
	
	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
