package com.zeeshan.is.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zeeshan.is.entity.InventoryItem;
import com.zeeshan.is.repository.InventoryItemRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InventoryController {

	@Autowired
	private InventoryItemRepository itemRepository;

	@GetMapping("/api/inventory/{productCode}")
	public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable("productCode") String productCode) {

		log.info("Finding inventory for product code :" + productCode);

		Optional<InventoryItem> inventoryItem = itemRepository.findByProductCode(productCode);

		if (inventoryItem.isPresent()) {
			return new ResponseEntity(inventoryItem, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/api/inventory")
	public List<InventoryItem> getInventory() {

		log.info("Finding inventory for all products ");
		 return itemRepository.findAll();
	}

}
