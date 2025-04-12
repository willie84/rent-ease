package com.example.rentease.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "units")
@Data
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "property_id")
    private Long propertyId;
    private String unitNumber;
    private String type;
    private double rentAmount;
}
