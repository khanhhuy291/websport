package com.example.websport.repository;

import com.example.websport.model.UsedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsedProductRepository extends JpaRepository<UsedProduct, Integer> {
    List<UsedProduct> findByBookedCourtId(Integer bookedCourtId);
}
