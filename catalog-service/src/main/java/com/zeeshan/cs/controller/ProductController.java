package com.zeeshan.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeeshan.cs.entity.Product;
import com.zeeshan.cs.exception.ProductNotFoundException;
import com.zeeshan.cs.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public List<Product> allProducts() {

		return productService.findAllProducts();
	}

	@GetMapping("/{code}")
	public Product productByCode(@PathVariable String code) {
		log.info("searching for prodcut with code: " + code);
		return productService.findProductByCode(code)
				.orElseThrow(() -> new ProductNotFoundException("Product with code [" + code + "] doesn't exist"));
	}

}
