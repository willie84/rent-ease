package com.example.rentease.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "leases")
@Data
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unit_id")
    private Long unitId;
    @Column(name = "tenant_id")
    private Long tenantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentAmount;
}
