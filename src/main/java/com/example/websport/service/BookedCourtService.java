package com.example.websport.service;

import com.example.websport.model.BookedCourt;
import com.example.websport.repository.BookedCourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookedCourtService {

    @Autowired
    private BookedCourtRepository bookedCourtRepository;

    public List<BookedCourt> getAllBookedCourts() {
        return bookedCourtRepository.findAll();
    }

    public Page<BookedCourt> getAllBookedCourts(Pageable pageable) {
        return bookedCourtRepository.findAll(pageable);
    }

    public Optional<BookedCourt> getBookedCourtById(Integer id) {
        return bookedCourtRepository.findById(id);
    }

    public List<BookedCourt> getBookedCourtsByBookingId(Integer bookingId) {
        return bookedCourtRepository.findByBookingId(bookingId);
    }

    public Page<BookedCourt> getBookedCourtsByBookingId(Integer bookingId, Pageable pageable) {
        return bookedCourtRepository.findByBookingId(bookingId, pageable);
    }

    public List<BookedCourt> getBookedCourtsByChildCourtId(Integer childCourtId) {
        return bookedCourtRepository.findByChildCourtId(childCourtId);
    }

    public Page<BookedCourt> getBookedCourtsByChildCourtId(Integer childCourtId, Pageable pageable) {
        return bookedCourtRepository.findByChildCourtId(childCourtId, pageable);
    }

    public List<BookedCourt> getBookedCourtsByBookingIdAndChildCourtId(Integer bookingId, Integer childCourtId) {
        return bookedCourtRepository.findByBookingIdAndChildCourtId(bookingId, childCourtId);
    }

    public Page<BookedCourt> getBookedCourtsByBookingIdAndChildCourtId(Integer bookingId, Integer childCourtId,
            Pageable pageable) {
        return bookedCourtRepository.findByBookingIdAndChildCourtId(bookingId, childCourtId, pageable);
    }

    public BookedCourt createBookedCourt(BookedCourt bookedCourt) {
        bookedCourt.setId(null);
        return bookedCourtRepository.save(bookedCourt);
    }

    public void deleteBookedCourt(Integer id) {
        bookedCourtRepository.deleteById(id);
    }
}
