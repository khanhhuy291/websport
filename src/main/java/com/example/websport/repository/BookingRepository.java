package com.example.websport.repository;

import com.example.websport.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Page<Booking> findByUserId(Integer userId, Pageable pageable);

    List<Booking> findByUserId(Integer userId);

    Page<Booking> findByBookingDate(LocalDate bookingDate, Pageable pageable);

    List<Booking> findByBookingDate(LocalDate bookingDate);

    Page<Booking> findByUserIdAndBookingDate(Integer userId, LocalDate bookingDate, Pageable pageable);

    List<Booking> findByUserIdAndBookingDate(Integer userId, LocalDate bookingDate);
}
