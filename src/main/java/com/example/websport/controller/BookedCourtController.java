package com.example.websport.controller;

import com.example.websport.model.BookedCourt;
import com.example.websport.service.BookedCourtService;
import com.example.websport.service.UserService;

import com.example.websport.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/booked-courts")
@CrossOrigin(origins = "*")
public class BookedCourtController {

    @Autowired
    private BookedCourtService bookedCourtService;

    @Autowired
    private UserService userService;

    // Lấy danh sách có phân trang
    @GetMapping
    public Page<BookedCourt> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookedDate", "startDate", "endDate").descending());
        return bookedCourtService.getAll(pageable);
    }

    @GetMapping("/customerId")
    public Page<BookedCourt> getBookedCourtsByCustomerId(
            @RequestParam int customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookedDate", "startDate", "endDate").descending());
        return bookedCourtService.getBookedCourtsByCustomerId(customerId, pageable);
    }

    // Lấy theo id
    @GetMapping("/{id}")
    public BookedCourt getById(@PathVariable Integer id) {
        return bookedCourtService.getById(id);
    }

    // Tạo mới
    @PostMapping
    public BookedCourt create(@RequestBody BookedCourt bookedCourt) {
        return bookedCourtService.create(bookedCourt);
    }

    // Sửa
    @PutMapping("/{id}")
    public BookedCourt update(@PathVariable Integer id, @RequestBody BookedCourt bookedCourt) {
        return bookedCourtService.update(id, bookedCourt);
    }

    // Xoá
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bookedCourtService.delete(id);
    }

    @GetMapping("/by-date")
    public List<BookedCourt> getBookedCourtsByDate(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString); // Assuming dateString is in YYYY-MM-DD format
        // Sorting should ideally be handled in the service layer when fetching the data
        return bookedCourtService.getBookedCourtsByDate(date);
    }

    // Tìm kiếm bookedCourt theo Tháng và Ngày
    @GetMapping("/by-month-day")
    public List<BookedCourt> getBookedCourtsByMonthAndDay(
            @RequestParam("month") int month,
            @RequestParam("day") int day) {
        // Sorting should ideally be handled in the service layer when fetching the data
        return bookedCourtService.getBookedCourtsByMonthAndDay(month, day);
    }

    // API to get user name by userId
    @GetMapping("/user/{userId}/name")
    public ResponseEntity<String> getUserName(@PathVariable Integer userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user.getFullName());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving user name");
        }
    }
}