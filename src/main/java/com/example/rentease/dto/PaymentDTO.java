package com.example.rentease.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDTO {
    private Long id;
    private Long leaseId;
    private double amount;
    private LocalDate paymentDate;
}