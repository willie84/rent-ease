package com.example.rentease.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tenants")
@Data
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInfo;
}