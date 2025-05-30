package com.example.websport.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UsedProduct")
public class UsedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer bookedCourtId;

    @Column(nullable = false)
    private Integer productId;

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBookedCourtId() {
        return bookedCourtId;
    }

    public void setBookedCourtId(Integer bookedCourtId) {
        this.bookedCourtId = bookedCourtId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
