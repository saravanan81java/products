package com.product.details.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.details.dto.ProductResponse;
import com.product.details.model.Product;
import com.product.details.model.ProductPrice;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class ProductClient {

	private final ObjectMapper objectMapper;
	private final WebClient webClient;
	
	@Autowired
	public ProductClient(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
		this.webClient = webClientBuilder.baseUrl("https://s3.eu-west-1.amazonaws.com").build();
        this.objectMapper = objectMapper;
    }
	
	public Mono<List<ProductResponse>> getProducts(String type) {
        Mono<Set<Product>> productsMono = fetchProducts();
        Mono<Set<ProductPrice>> pricesMono = fetchPrices();

        return Mono.zip(productsMono, pricesMono)
                .map(tuple -> {
                    Set<Product> products = tuple.getT1();
                    Set<ProductPrice> prices = tuple.getT2();

                    Map<String, ProductPrice> priceMap = prices.stream()
                            .collect(Collectors.toMap(ProductPrice::getProduct_uid, price -> price));

                    List<ProductResponse> response = new ArrayList<>();

                    for (Product product : products) {
                        ProductPrice price = priceMap.get(product.getProduct_uid());
                        if (price != null) {
                            if (type == null || product.getProduct_type().equalsIgnoreCase(type)) {
                                ProductResponse productResponse = new ProductResponse();
                                productResponse.setProductUid(product.getProduct_uid());
                                productResponse.setProductType(product.getProduct_type());
                                productResponse.setName(product.getName());
                                productResponse.setFullUrl(product.getFull_url());
                                productResponse.setUnitPrice(price.getUnit_price());
                                productResponse.setUnitPriceMeasure(price.getUnit_price_measure());
                                productResponse.setUnitPriceMeasureAmount(price.getUnit_price_measure_amount());
                                response.add(productResponse);
                            }
                        }
                    }

                    return response;
                });
    }

	
	
	private Mono<Set<Product>> fetchProducts() {
		//this.webClient = WebClient.builder().baseUrl("https://s3.eu-west-1.amazonaws.com").build();
        return this.webClient.get()
                .uri("/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_v2.json")
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseProducts);
    }

    private Mono<Set<ProductPrice>> fetchPrices() {
    	//WebClient webClient = WebClient.builder().baseUrl("https://s3.eu-west-1.amazonaws.com").build();
        return this.webClient.get()
                .uri("/hackajob-assets1.p.hackajob/challenges/sainsbury_products/products_price_v2.json")
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parsePrices);
    }

    private Set<Product> parseProducts(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Set<Product>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse products", e);
        }
    }

    private Set<ProductPrice> parsePrices(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Set<ProductPrice>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse prices", e);
        }
    }
}
