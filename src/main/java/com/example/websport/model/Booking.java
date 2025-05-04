package com.example.websport.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Booking")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bookingDate", nullable = false)
    private LocalDate bookingDate = LocalDate.now();

    @Column(name = "selloff", precision = 10, scale = 2)
    private BigDecimal selloff;

    @Column(name = "note", length = 250)
    private String note;

    @Column(name = "status", length = 50)
    private String status = "PENDING";

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getSelloff() {
        return selloff;
    }

    public void setSelloff(BigDecimal selloff) {
        this.selloff = selloff;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
