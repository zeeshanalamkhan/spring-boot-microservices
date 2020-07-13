package com.zeeshan.cs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zeeshan.cs.proxy.FeignInventoryServiceClient;
import com.zeeshan.cs.web.ProductInventoryResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceClient {

	/*
	 * @Autowired private RestTemplate restTemplate;
	 */
	
	@Autowired
	private FeignInventoryServiceClient feignClient;

	/*
	 * @HystrixCommand(commandKey = "inventory-by-productCOde", fallbackMethod =
	 * "getDefaultProductInventoryByCode", commandProperties = {
	 * 
	 * @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
	 * })
	 */

	/*
	 * @HystrixCommand(commandKey = "inventory-by-productCOde", fallbackMethod =
	 * "getDefaultProductInventoryByCode") public Optional<ProductInventoryResponse>
	 * getProductInvemtoryByCode(String productCode) {
	 * 
	 * log.info("getProductInventoryByCode: " + productCode);
	 * 
	 * ResponseEntity<ProductInventoryResponse> itemResponseEntity =
	 * restTemplate.getForEntity( "http://inventory-service/api/inventory/{code}",
	 * ProductInventoryResponse.class, productCode);
	 * 
	 * if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
	 * 
	 * return Optional.ofNullable(itemResponseEntity.getBody()); } else {
	 * log.error("Unable to get inventory level for product_code: " + productCode +
	 * ", StatusCode: " + itemResponseEntity.getStatusCode()); return
	 * Optional.empty(); } }
	 * 
	 */

	@HystrixCommand(commandKey = "inventory-by-productCOde", fallbackMethod = "getDefaultProductInventoryByCode")
	public Optional<ProductInventoryResponse> getProductInvemtoryByCode(String productCode) {

		log.info("getProductInventoryByCode: " + productCode);

		Optional<ProductInventoryResponse> itemResponseEntity = feignClient.getProductInvemtoryByCode(productCode);

		if (itemResponseEntity.isPresent()) {

			return itemResponseEntity;
		} else {
			log.error("Unable to get inventory level for product_code: " + productCode);
			return Optional.empty();
		}
	}

	public Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {

		log.info("Returning default ProductInventoryByCode for productCode: " + productCode);

		ProductInventoryResponse productInventoryResponse = new ProductInventoryResponse();
		productInventoryResponse.setProductCode(productCode);
		productInventoryResponse.setAvailableQuantity(50);
		return Optional.ofNullable(productInventoryResponse);
	}
}
