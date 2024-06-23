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

import com.product.details.service.ProductService;

@WebMvcTest(ProductsController.class)
@AutoConfigureMockMvc
public class ProductsControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ProductService productService;
	
	
	@BeforeEach
	public void setUp() {
		 
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
