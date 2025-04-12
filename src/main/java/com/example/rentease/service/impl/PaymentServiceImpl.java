package com.example.rentease.service.impl;

import com.example.rentease.dto.PaymentDTO;
import com.example.rentease.model.Payment;
import com.example.rentease.repository.PaymentRepository;
import com.example.rentease.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDTO> getPaymentById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(this::convertToDTO);
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment payment = convertToEntity(paymentDTO);
            payment.setId(id);
            Payment updatedPayment = paymentRepository.save(payment);
            return convertToDTO(updatedPayment);
        } else {
            return null;
        }
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setLeaseId(payment.getLeaseId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        return paymentDTO;
    }

    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setLeaseId(paymentDTO.getLeaseId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        return payment;
    }
}
