package com.example.websport.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "BookedCourt")
@NoArgsConstructor
@AllArgsConstructor
public class BookedCourt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDatetime;

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDatetime;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "isCheckin")
    private Boolean isCheckin = false;

    @Column(name = "selloff")
    private Double selloff;

    @Column(name = "BookingID", nullable = false)
    private Integer bookingId;

    @Column(name = "ChildCourtID", nullable = false)
    private Integer childCourtId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return endDatetime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(Boolean checkin) {
        isCheckin = checkin;
    }

    public Double getSelloff() {
        return selloff;
    }

    public void setSelloff(Double selloff) {
        this.selloff = selloff;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getChildCourtId() {
        return childCourtId;
    }

    public void setChildCourtId(Integer childCourtId) {
        this.childCourtId = childCourtId;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

}
