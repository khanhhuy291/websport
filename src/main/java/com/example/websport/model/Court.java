package com.example.websport.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courts")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String status; // AVAILABLE / MAINTENANCE
    @Column(name = "image_url")
    private String imageUrl; // Tên file ảnh, ví dụ: court1.jpg

    // Constructors
    public Court() {
    }

    public Court(String name, String address, String status, String imageUrl) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
