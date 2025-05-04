package com.example.websport.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.websport.model.UsedProduct;

public interface UsedProductRepository
        extends JpaRepository<UsedProduct, Integer>, PagingAndSortingRepository<UsedProduct, Integer> {
    Page<UsedProduct> findAll(Pageable pageable);
}
