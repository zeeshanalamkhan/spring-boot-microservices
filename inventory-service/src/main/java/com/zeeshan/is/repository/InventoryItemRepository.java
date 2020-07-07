package com.zeeshan.is.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.is.entity.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

	Optional<InventoryItem> findByProductCode(String productCode);

}
