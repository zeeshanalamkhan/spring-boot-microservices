package com.zeeshan.cs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeeshan.cs.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public Optional<Product> findByCode(String code);

}
