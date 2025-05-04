package com.example.websport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.websport.model.UsedProduct;
import com.example.websport.repository.UsedProductRepository;
import java.util.List;

@Service
public class UsedProductService {
    @Autowired
    private UsedProductRepository usedProductRepository;

    public List<UsedProduct> getAllUsedProducts() {
        return usedProductRepository.findAll();
    }

    public Page<UsedProduct> getAllUsedProducts(Pageable pageable) {
        return usedProductRepository.findAll(pageable);
    }

    public UsedProduct getUsedProductById(Integer id) {
        return usedProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsedProduct not found with id: " + id));
    }

    public UsedProduct createUsedProduct(UsedProduct usedProduct) {
        return usedProductRepository.save(usedProduct);
    }

    public UsedProduct updateUsedProduct(Integer id, UsedProduct updatedUsedProduct) {
        UsedProduct existingUsedProduct = getUsedProductById(id);

        // Only update fields that are provided in the request
        if (updatedUsedProduct.getQuantity() != null) {
            existingUsedProduct.setQuantity(updatedUsedProduct.getQuantity());
        }

        if (updatedUsedProduct.getPrice() != null) {
            existingUsedProduct.setPrice(updatedUsedProduct.getPrice());
        }

        if (updatedUsedProduct.getSelloff() != null) {
            existingUsedProduct.setSelloff(updatedUsedProduct.getSelloff());
        }

        if (updatedUsedProduct.getBookedCourtID() != null) {
            existingUsedProduct.setBookedCourtID(updatedUsedProduct.getBookedCourtID());
        }

        if (updatedUsedProduct.getProductId() != null) {
            existingUsedProduct.setProductId(updatedUsedProduct.getProductId());
        }

        return usedProductRepository.save(existingUsedProduct);
    }

    public void deleteUsedProduct(Integer id) {
        usedProductRepository.deleteById(id);
    }
}
