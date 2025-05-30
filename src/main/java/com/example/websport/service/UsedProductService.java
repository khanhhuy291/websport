package com.example.websport.service;

import com.example.websport.model.UsedProduct;
import com.example.websport.repository.UsedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsedProductService {
    @Autowired
    private UsedProductRepository usedProductRepository;

    public Page<UsedProduct> getAll(Pageable pageable) {
        return usedProductRepository.findAll(pageable);
    }

    public UsedProduct getById(Integer id) {
        return usedProductRepository.findById(id).orElse(null);
    }

    public UsedProduct create(UsedProduct usedProduct) {
        return usedProductRepository.save(usedProduct);
    }

    public UsedProduct update(Integer id, UsedProduct usedProduct) {
        usedProduct.setId(id);
        return usedProductRepository.save(usedProduct);
    }

    public void delete(Integer id) {
        usedProductRepository.deleteById(id);
    }

    // In UsedProductService.java
    public List<UsedProduct> findByBookedCourtId(Integer bookedCourtId) {
        return usedProductRepository.findByBookedCourtId(bookedCourtId);
    }
}
