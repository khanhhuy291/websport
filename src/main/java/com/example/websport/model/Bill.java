package com.example.websport.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bill")

public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "paymentDate")
    private LocalDate paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "paymentType")
    private String paymentType;

    @Column(name = "note")
    private String note;

    @Column(name = "bookingID")
    private Integer bookingID;

    @Column(name = "userID")
    private Integer userID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate localDate) {
        this.paymentDate = localDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
