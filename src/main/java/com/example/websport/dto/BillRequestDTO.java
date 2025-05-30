package com.example.websport.dto;

public class BillRequestDTO {
    private String paymentType;
    private String note;

    // Getters and Setters
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
}