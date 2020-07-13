package com.zeeshan.cs.proxy;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zeeshan.cs.web.ProductInventoryResponse;

@FeignClient(name = "inventory-service")
public interface FeignInventoryServiceClient {

	@GetMapping("/api/inventory/{code}")
	public Optional<ProductInventoryResponse> getProductInvemtoryByCode(@PathVariable("code") String productCode);

}
