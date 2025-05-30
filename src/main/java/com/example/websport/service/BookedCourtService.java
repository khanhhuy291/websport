package com.example.websport.service;

import com.example.websport.model.BookedCourt;
import com.example.websport.repository.BookedCourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookedCourtService {
    @Autowired
    private BookedCourtRepository bookedCourtRepository;

    public Page<BookedCourt> getAll(Pageable pageable) {
        return bookedCourtRepository.findAll(pageable);
    }

    public BookedCourt getById(Integer id) {
        return bookedCourtRepository.findById(id).orElse(null);
    }

    public BookedCourt create(BookedCourt bookedCourt) {
        return bookedCourtRepository.save(bookedCourt);
    }

    public Page<BookedCourt> getBookedCourtsByCustomerId(int customerId, Pageable pageable) {
        return bookedCourtRepository.findByCustomerId(customerId, pageable);
    }

    public BookedCourt update(Integer id, BookedCourt bookedCourtDetails) {
        BookedCourt existingBookedCourt = bookedCourtRepository.findById(id).orElse(null);
        if (existingBookedCourt == null) {
            return null; // Or throw an exception
        }

        // Update only non-null fields from bookedCourtDetails
        if (bookedCourtDetails.getCourtId() != null) {
            existingBookedCourt.setCourtId(bookedCourtDetails.getCourtId());
        }
        if (bookedCourtDetails.getUserId() != null) {
            existingBookedCourt.setUserId(bookedCourtDetails.getUserId());
        }
        if (bookedCourtDetails.getCustomerId() != null) {
            existingBookedCourt.setCustomerId(bookedCourtDetails.getCustomerId());
        }
        if (bookedCourtDetails.getBookedDate() != null) {
            existingBookedCourt.setBookedDate(bookedCourtDetails.getBookedDate());
        }
        if (bookedCourtDetails.getStartDate() != null) {
            existingBookedCourt.setStartDate(bookedCourtDetails.getStartDate());
        }
        if (bookedCourtDetails.getEndDate() != null) {
            existingBookedCourt.setEndDate(bookedCourtDetails.getEndDate());
        }
        if (bookedCourtDetails.getStatus() != null) {
            existingBookedCourt.setStatus(bookedCourtDetails.getStatus());
        }
        if (bookedCourtDetails.getIsCheckin() != null) {
            existingBookedCourt.setIsCheckin(bookedCourtDetails.getIsCheckin());
        }
        if (bookedCourtDetails.getNote() != null) {
            existingBookedCourt.setNote(bookedCourtDetails.getNote());
        }

        return bookedCourtRepository.save(existingBookedCourt);
    }

    public void delete(Integer id) {
        bookedCourtRepository.deleteById(id);
    }

    public List<BookedCourt> getBookedCourtsByDate(LocalDate date) {
        return bookedCourtRepository.findByBookedDate(date);
    }

    public List<BookedCourt> getBookedCourtsByMonthAndDay(int month, int day) {
        return bookedCourtRepository.findByMonthAndDay(month, day);
    }
}
