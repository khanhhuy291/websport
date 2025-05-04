package com.example.websport.service;

import com.example.websport.model.Booking;
import com.example.websport.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Page<Booking> getAllBookings(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public Page<Booking> getBookingsByUserId(Integer userId, Pageable pageable) {
        return bookingRepository.findByUserId(userId, pageable);
    }

    public List<Booking> getBookingsByUserId(Integer userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Page<Booking> getBookingsByDate(LocalDate bookingDate, Pageable pageable) {
        return bookingRepository.findByBookingDate(bookingDate, pageable);
    }

    public List<Booking> getBookingsByDate(LocalDate bookingDate) {
        return bookingRepository.findByBookingDate(bookingDate);
    }

    public Page<Booking> getBookingsByUserIdAndDate(Integer userId, LocalDate bookingDate, Pageable pageable) {
        return bookingRepository.findByUserIdAndBookingDate(userId, bookingDate, pageable);
    }

    public List<Booking> getBookingsByUserIdAndDate(Integer userId, LocalDate bookingDate) {
        return bookingRepository.findByUserIdAndBookingDate(userId, bookingDate);
    }

    public Booking createBooking(Booking booking) {
        booking.setId(null);
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Integer id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Only update fields that are provided in the request
        if (updatedBooking.getBookingDate() != null) {
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
        }

        if (updatedBooking.getSelloff() != null) {
            existingBooking.setSelloff(updatedBooking.getSelloff());
        }

        if (updatedBooking.getNote() != null) {
            existingBooking.setNote(updatedBooking.getNote());
        }

        if (updatedBooking.getStatus() != null) {
            existingBooking.setStatus(updatedBooking.getStatus());
        }

        if (updatedBooking.getUserId() != null) {
            existingBooking.setUserId(updatedBooking.getUserId());
        }

        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
