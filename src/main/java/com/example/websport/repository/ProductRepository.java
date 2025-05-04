package com.example.websport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.websport.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
