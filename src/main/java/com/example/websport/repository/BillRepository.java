package com.example.websport.repository;

import com.example.websport.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Page<Bill> findByUserID(Integer userID, Pageable pageable);

    Page<Bill> findByBookingID(Integer bookingID, Pageable pageable);
}
