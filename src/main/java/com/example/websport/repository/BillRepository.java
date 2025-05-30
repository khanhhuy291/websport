package com.example.websport.repository;

import com.example.websport.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b WHERE YEAR(b.paymentDate) = :year")
    Page<Bill> findByYear(@Param("year") int year, Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE MONTH(b.paymentDate) = :month AND YEAR(b.paymentDate) = :year")
    Page<Bill> findByMonthAndYear(@Param("month") int month, @Param("year") int year, Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE DAY(b.paymentDate) = :day AND MONTH(b.paymentDate) = :month AND YEAR(b.paymentDate) = :year")
    Page<Bill> findByDayMonthAndYear(@Param("day") int day, @Param("month") int month, @Param("year") int year,
            Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.customerId = :customerId")
    Page<Bill> findByCustomerId(@Param("customerId") int customerId, Pageable pageable);
}