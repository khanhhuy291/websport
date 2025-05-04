package com.example.websport.model;

import jakarta.persistence.*;

@Entity
@Table(name = "child_courts")
public class ChildCourts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "courts_id")
    private Long courtsId;
    private Double price_per_hour;
    private String status;
    private String type;

    public ChildCourts() {
    }

    public ChildCourts(String name, Long courts_id, Double price_per_hour, String status, String type) {
        this.name = name;
        this.courtsId = courts_id;
        this.price_per_hour = price_per_hour;
        this.status = status;
        this.type = type;
    }

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

    public Long getCourts_id() {
        return courtsId;
    }

    public void setCourts_id(Long courts_id) {
        this.courtsId = courts_id;
    }

    public Double getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(Double price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
