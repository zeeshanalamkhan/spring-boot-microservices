package com.zeeshan.cs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.zeeshan.cs.entity.Product;
import com.zeeshan.cs.repository.ProductRepository;
import com.zeeshan.cs.web.ProductInventoryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Product> findAllProducts() {

		List<Product> products = productRepository.findAll();
		return products;
	}

	@Override
	public Optional<Product> findProductByCode(String code) {

		Optional<Product> product = productRepository.findByCode(code);

		if (product.isPresent()) {

			log.info("Fetching inventory level for product_code: " + code);
			ResponseEntity<ProductInventoryResponse> itemResponseEntity = restTemplate.getForEntity(
					"http://inventory-service/api/inventory/{code}", ProductInventoryResponse.class, code);

			if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {

				Integer quantity = itemResponseEntity.getBody().getAvailableQuantity();
				log.info("Available quantity: " + quantity);

				product.get().setInStock(quantity > 0);
			} else {
				log.error("Unable to get inventory level for product_code: " + code + ", StatusCode: "
						+ itemResponseEntity.getStatusCode());
			}
		}
		return product;
	}

}
