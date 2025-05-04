package com.example.websport.controller;

import com.example.websport.model.Booking;
import com.example.websport.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public Page<Booking> getBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Pageable pageable = PageRequest.of(page, size);

        if (id != null) {
            return bookingService.getBookingById(id)
                    .map(booking -> new org.springframework.data.domain.PageImpl<>(
                            java.util.Collections.singletonList(booking),
                            pageable,
                            1))
                    .orElse(new org.springframework.data.domain.PageImpl<>(
                            java.util.Collections.emptyList()));
        }

        if (userId != null && date != null) {
            return bookingService.getBookingsByUserIdAndDate(userId, date, pageable);
        }

        if (userId != null) {
            return bookingService.getBookingsByUserId(userId, pageable);
        }

        if (date != null) {
            return bookingService.getBookingsByDate(date, pageable);
        }

        return bookingService.getAllBookings(pageable);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Integer id, @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(id, updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
    }
}
