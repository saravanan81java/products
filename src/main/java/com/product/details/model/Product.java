package com.product.details.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private String product_uid;
    private String product_type;
    private String name;
    private String full_url;
}
