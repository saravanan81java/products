package com.product.details.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.details.service.ProductClient;

@WebMvcTest(ProductsController.class)
@AutoConfigureMockMvc
public class ProductsControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProductClient productClient;
	
	@MockBean
	private ObjectMapper objectMapper;
	
	@MockBean
	private WebClient webClient;
	
	
	@BeforeEach
	public void setUp() {
		productClient = new ProductClient(WebClient.builder(), objectMapper);
	}
	
	@Test
	public void pingTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/ping"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void getProductsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/products?type=BASIC2"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void getAllProductsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/products"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
}
