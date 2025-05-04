package com.example.websport.repository;

import com.example.websport.model.BookedCourt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface BookedCourtRepository
        extends JpaRepository<BookedCourt, Integer>, PagingAndSortingRepository<BookedCourt, Integer> {
    Page<BookedCourt> findAll(Pageable pageable);

    Page<BookedCourt> findByBookingId(Integer bookingId, Pageable pageable);

    List<BookedCourt> findByBookingId(Integer bookingId);

    Page<BookedCourt> findByChildCourtId(Integer childCourtId, Pageable pageable);

    List<BookedCourt> findByChildCourtId(Integer childCourtId);

    Page<BookedCourt> findByBookingIdAndChildCourtId(Integer bookingId, Integer childCourtId, Pageable pageable);

    List<BookedCourt> findByBookingIdAndChildCourtId(Integer bookingId, Integer childCourtId);
}
