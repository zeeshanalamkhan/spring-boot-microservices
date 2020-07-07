package com.zeeshan.cs.web;

import lombok.Data;

@Data
public class ProductInventoryResponse {

	private String productCode;
	private Integer availableQuantity = 0;
	

}
