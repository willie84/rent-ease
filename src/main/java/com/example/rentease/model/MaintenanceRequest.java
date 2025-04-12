package com.example.rentease.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance_requests")
@Data
public class MaintenanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unit_id")
    private Long unitId;
    private String description;
    private LocalDate requestDate;
    private String status;
}
