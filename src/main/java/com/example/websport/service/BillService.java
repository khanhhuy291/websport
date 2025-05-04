package com.example.websport.service;

import com.example.websport.model.Bill;
import com.example.websport.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public Page<Bill> getAllBills(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Integer id) {
        return billRepository.findById(id).orElse(null);
    }

    public Page<Bill> getBillByUserId(Integer userid, Pageable pageable) {
        return billRepository.findByUserID(userid, pageable);
    }

    public Page<Bill> getBillByBookingId(Integer bookingID, Pageable pageable) {
        return billRepository.findByBookingID(bookingID, pageable);
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill updateBill(Integer id, Bill bill) {
        Bill existingBill = billRepository.findById(id).orElse(null);
        if (existingBill == null) {
            throw new RuntimeException("Bill not found");
        }
        return billRepository.save(bill);
    }

    public void deleteBill(Integer id) {
        billRepository.deleteById(id);
    }

}
