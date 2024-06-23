package com.product.details.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

	private String productUid;
    private String productType;
    private String name;
    private String fullUrl;
    private double unitPrice;
    private String unitPriceMeasure;
    private int unitPriceMeasureAmount;
}
