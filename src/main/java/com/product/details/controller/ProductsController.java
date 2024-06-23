package com.product.details.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.details.dto.ProductResponse;
import com.product.details.service.ProductClient;

import reactor.core.publisher.Mono;

@RestController
public class ProductsController {

	@Autowired
	private ProductClient productClient;
	
	
	@GetMapping("/ping")
	public String pingAction() {
		return "ping!!!!";
	}
	
	@GetMapping("/products")
	public Mono<List<ProductResponse>> getProducts(@RequestParam(required = false) String type){
		return productClient.getProducts(type);
	}
}
