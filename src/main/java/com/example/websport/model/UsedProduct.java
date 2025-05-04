package com.example.websport.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "UsedProduct")
public class UsedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "selloff", nullable = false)
    private BigDecimal selloff;

    @Column(name = "bookedCourtID", nullable = false)
    private Integer bookedCourtID;

    @Column(name = "productId", nullable = false)
    private Integer productId;

    // Getters and Setters
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSelloff() {
        return selloff;
    }

    public void setSelloff(BigDecimal selloff) {
        this.selloff = selloff;
    }

    public Integer getBookedCourtID() {
        return bookedCourtID;
    }

    public void setBookedCourtID(Integer bookedCourtID) {
        this.bookedCourtID = bookedCourtID;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}