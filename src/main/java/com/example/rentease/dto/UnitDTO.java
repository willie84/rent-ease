package com.example.rentease.dto;

import lombok.Data;

@Data
public class UnitDTO {
    private Long id;
    private Long propertyId;
    private String unitNumber;
    private String type;
    private double rentAmount;
}