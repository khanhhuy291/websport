package com.example.websport.service;

import com.example.websport.model.Bill;
import com.example.websport.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Page<Bill> getAllBills(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id: " + id));
    }

    public Page<Bill> getBillsByYear(int year, Pageable pageable) {
        return billRepository.findByYear(year, pageable);
    }

    public Page<Bill> getBillsByMonthAndYear(int month, int year, Pageable pageable) {
        return billRepository.findByMonthAndYear(month, year, pageable);
    }

    public Page<Bill> getBillsByDayMonthAndYear(int day, int month, int year, Pageable pageable) {
        return billRepository.findByDayMonthAndYear(day, month, year, pageable);
    }

    public Page<Bill> getBillsByCustomerId(int customerId, Pageable pageable) {
        return billRepository.findByCustomerId(customerId, pageable);
    }

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill updateBill(Long id, Bill bill) {
        Bill existingBill = getBillById(id);
        existingBill.setPaymentDate(bill.getPaymentDate());
        existingBill.setPaymentType(bill.getPaymentType());
        existingBill.setTotal(bill.getTotal());
        existingBill.setNote(bill.getNote());
        existingBill.setBookedCourtId(bill.getBookedCourtId());
        existingBill.setUserId(bill.getUserId());
        existingBill.setCustomerId(bill.getCustomerId());
        return billRepository.save(existingBill);
    }

    public void deleteBill(Long id) {
        Bill bill = getBillById(id);
        billRepository.delete(bill);
    }
}