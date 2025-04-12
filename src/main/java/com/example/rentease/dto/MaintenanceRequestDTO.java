package com.example.rentease.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceRequestDTO {
    private Long id;
    private Long unitId;
    private String description;
    private LocalDate requestDate;
    private String status;
}