package com.example.websport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.example.websport.model.UsedProduct;
import com.example.websport.service.UsedProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

@RestController
@RequestMapping("/api/used-products")
@CrossOrigin(origins = "*")
public class UsedProductController {
    @Autowired
    private UsedProductService usedProductService;

    @GetMapping
    public Page<UsedProduct> getAllUsedProducts(
            @RequestParam(required = false) Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (id != null) {
            try {
                UsedProduct usedProduct = usedProductService.getUsedProductById(id);
                return new PageImpl<>(
                        Collections.singletonList(usedProduct),
                        pageable,
                        1);
            } catch (Exception e) {
                return new PageImpl<>(
                        Collections.emptyList(),
                        pageable,
                        0);
            }
        }

        return usedProductService.getAllUsedProducts(pageable);
    }

    @PostMapping
    public ResponseEntity<UsedProduct> createUsedProduct(@RequestBody UsedProduct usedProduct) {
        try {
            UsedProduct createdUsedProduct = usedProductService.createUsedProduct(usedProduct);
            return ResponseEntity.ok(createdUsedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsedProduct> updateUsedProduct(@PathVariable Integer id,
            @RequestBody UsedProduct usedProduct) {
        try {
            UsedProduct updatedUsedProduct = usedProductService.updateUsedProduct(id, usedProduct);
            return ResponseEntity.ok(updatedUsedProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsedProduct(@PathVariable Integer id) {
        usedProductService.deleteUsedProduct(id);
        return ResponseEntity.noContent().build();
    }
}