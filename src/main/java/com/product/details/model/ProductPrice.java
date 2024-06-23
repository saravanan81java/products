package com.product.details.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {
	private String product_uid;
    private double unit_price;
    private String unit_price_measure;
    private int unit_price_measure_amount;
}
