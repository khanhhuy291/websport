package com.example.websport.controller;

import com.example.websport.model.UsedProduct;
import com.example.websport.service.UsedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/used-products")
@CrossOrigin(origins = "*")
public class UsedProductController {

    @Autowired
    private UsedProductService usedProductService;

    // Lấy danh sách có phân trang
    @GetMapping
    public Page<UsedProduct> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usedProductService.getAll(pageable);
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public UsedProduct getById(@PathVariable Integer id) {
        return usedProductService.getById(id);
    }

    // API to get UsedProducts by bookedCourtId
    @GetMapping("/by-booked-court/{bookedCourtId}")
    public List<UsedProduct> getUsedProductsByBookedCourtId(@PathVariable Integer bookedCourtId) {
        return usedProductService.findByBookedCourtId(bookedCourtId);
    }

    // Tạo mới
    @PostMapping
    public UsedProduct create(@RequestBody UsedProduct usedProduct) {
        return usedProductService.create(usedProduct);
    }

    // Sửa
    @PutMapping("/{id}")
    public UsedProduct update(@PathVariable Integer id, @RequestBody UsedProduct usedProduct) {
        return usedProductService.update(id, usedProduct);
    }

    // Xoá
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usedProductService.delete(id);
    }
}
