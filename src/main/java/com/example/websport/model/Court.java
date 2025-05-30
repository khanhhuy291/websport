package com.example.websport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Courts")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nameCourt", nullable = false, length = 100)
    private String nameCourt;

    @Column(name = "typeCourt", nullable = false, length = 50)
    private String typeCourt;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "imageUrl", length = 255)
    private String imageUrl;

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCourt() {
        return nameCourt;
    }

    public void setNameCourt(String nameCourt) {
        this.nameCourt = nameCourt;
    }

    public String getTypeCourt() {
        return typeCourt;
    }

    public void setTypeCourt(String typeCourt) {
        this.typeCourt = typeCourt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}