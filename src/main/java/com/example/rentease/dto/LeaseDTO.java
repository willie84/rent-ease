package com.example.rentease.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaseDTO {
    private Long id;
    private Long unitId;
    private Long tenantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentAmount;
}