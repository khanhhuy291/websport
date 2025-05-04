package com.example.websport.controller;

import com.example.websport.model.Bill;
import com.example.websport.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")

public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public Page<Bill> getBills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer bookingId) {

        Pageable pageable = PageRequest.of(page, size);

        if (id != null) {
            return new PageImpl<>(Collections.singletonList(billService.getBillById(id)), pageable, 1);
        }

        if (userId != null) {
            return billService.getBillByUserId(userId, pageable);
        }

        if (bookingId != null) {
            return billService.getBillByBookingId(bookingId, pageable);
        }

        return billService.getAllBills(pageable);
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billService.createBill(bill);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Integer id, @RequestBody Bill bill) {
        return billService.updateBill(id, bill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Integer id) {
        billService.deleteBill(id);
    }

}