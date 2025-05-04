package com.example.websport.controller;

import com.example.websport.model.BookedCourt;
import com.example.websport.service.BookedCourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booked-courts")
@CrossOrigin(origins = "*")
public class BookedCourtController {

    @Autowired
    private BookedCourtService bookedCourtService;

    @GetMapping
    public Page<BookedCourt> getBookedCourts(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer bookingId,
            @RequestParam(required = false) Integer childCourtId,
            Pageable pageable) {

        if (id != null) {
            return bookedCourtService.getBookedCourtById(id)
                    .map(court -> new org.springframework.data.domain.PageImpl<>(
                            java.util.Collections.singletonList(court),
                            pageable,
                            1))
                    .orElse(new org.springframework.data.domain.PageImpl<>(
                            java.util.Collections.emptyList()));
        }

        if (bookingId != null && childCourtId != null) {
            return bookedCourtService.getBookedCourtsByBookingIdAndChildCourtId(
                    bookingId, childCourtId, pageable);
        }

        if (bookingId != null) {
            return bookedCourtService.getBookedCourtsByBookingId(bookingId, pageable);
        }

        if (childCourtId != null) {
            return bookedCourtService.getBookedCourtsByChildCourtId(childCourtId, pageable);
        }

        return bookedCourtService.getAllBookedCourts(pageable);
    }

    @PostMapping
    public BookedCourt createBookedCourt(@RequestBody BookedCourt bookedCourt) {
        return bookedCourtService.createBookedCourt(bookedCourt);
    }

    @PutMapping("/{id}")
    public BookedCourt updateBookedCourt(@PathVariable Integer id, @RequestBody BookedCourt updatedBookedCourt) {
        return bookedCourtService.getBookedCourtById(id)
                .map(existingBookedCourt -> {
                    existingBookedCourt.setStartDatetime(updatedBookedCourt.getStartDatetime());
                    existingBookedCourt.setEndDatetime(updatedBookedCourt.getEndDatetime());
                    existingBookedCourt.setPrice(updatedBookedCourt.getPrice());
                    existingBookedCourt.setIsCheckin(updatedBookedCourt.getIsCheckin());
                    existingBookedCourt.setSelloff(updatedBookedCourt.getSelloff());
                    existingBookedCourt.setBookingId(updatedBookedCourt.getBookingId());
                    existingBookedCourt.setChildCourtId(updatedBookedCourt.getChildCourtId());
                    return bookedCourtService.createBookedCourt(existingBookedCourt);
                })
                .orElseThrow(() -> new RuntimeException("BookedCourt not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteBookedCourt(@PathVariable Integer id) {
        bookedCourtService.deleteBookedCourt(id);
    }
}