package com.product.details.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.details.dto.ProductResponse;

import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	ProductClient productClient;

	public Mono<List<ProductResponse>> getProducts(String type) {
		return productClient.getProducts(type);
	}
	
	
}
