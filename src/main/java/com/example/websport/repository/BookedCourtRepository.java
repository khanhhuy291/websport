package com.example.websport.repository;

import com.example.websport.model.BookedCourt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookedCourtRepository extends JpaRepository<BookedCourt, Integer> {
    // Có thể thêm các hàm tìm kiếm tuỳ ý nếu cần
    List<BookedCourt> findByBookedDate(LocalDate bookedDate);

    @Query("SELECT b FROM BookedCourt b WHERE MONTH(b.bookedDate) = :month AND DAY(b.bookedDate) = :day")
    List<BookedCourt> findByMonthAndDay(@Param("month") int month, @Param("day") int day);

    @Query("SELECT b FROM BookedCourt b WHERE b.customerId = :customerId")
    Page<BookedCourt> findByCustomerId(@Param("customerId") int customerId, Pageable pageable);
}
