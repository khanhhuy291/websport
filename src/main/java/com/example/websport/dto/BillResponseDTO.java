package com.example.websport.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class BillResponseDTO {
    // Bill/Payment Info
    private Long billId;
    private LocalDateTime paymentDate;
    private String paymentType;
    private String note;
    private BigDecimal totalAmount; // Overall total

    // User/Customer Info (IDs primarily, names can be added if needed by fetching)
    private Integer userId;
    private Integer customerId;

    // Booked Court Info
    private Integer bookedCourtId;
    private String courtName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String usageDurationFormatted; // e.g., "1h 30m"
    private BigDecimal courtSubtotal;

    // Used Products Info
    private List<UsedProductDetailDTO> usedProducts;
    private BigDecimal productsSubtotal;

    public BillResponseDTO() {
    }

    // Getters and Setters
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBookedCourtId() {
        return bookedCourtId;
    }

    public void setBookedCourtId(Integer bookedCourtId) {
        this.bookedCourtId = bookedCourtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getUsageDurationFormatted() {
        return usageDurationFormatted;
    }

    public void setUsageDurationFormatted(String usageDurationFormatted) {
        this.usageDurationFormatted = usageDurationFormatted;
    }

    public BigDecimal getCourtSubtotal() {
        return courtSubtotal;
    }

    public void setCourtSubtotal(BigDecimal courtSubtotal) {
        this.courtSubtotal = courtSubtotal;
    }

    public List<UsedProductDetailDTO> getUsedProducts() {
        return usedProducts;
    }

    public void setUsedProducts(List<UsedProductDetailDTO> usedProducts) {
        this.usedProducts = usedProducts;
    }

    public BigDecimal getProductsSubtotal() {
        return productsSubtotal;
    }

    public void setProductsSubtotal(BigDecimal productsSubtotal) {
        this.productsSubtotal = productsSubtotal;
    }
}