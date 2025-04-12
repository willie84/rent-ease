package com.example.rentease.service;

import com.example.rentease.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentDTO> getAllPayments();
    Optional<PaymentDTO> getPaymentById(Long id);
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);
    void deletePayment(Long id);
}
