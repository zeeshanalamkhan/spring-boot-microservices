package com.zeeshan.cs.service;

import java.util.List;
import java.util.Optional;

import com.zeeshan.cs.entity.Product;

public interface ProductService {

	public List<Product> findAllProducts();

	public Optional<Product> findProductByCode(String code);
	
}
