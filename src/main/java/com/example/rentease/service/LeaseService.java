package com.example.rentease.service;

import com.example.rentease.dto.LeaseDTO;

import java.util.List;
import java.util.Optional;

public interface LeaseService {
    List<LeaseDTO> getAllLeases();
    Optional<LeaseDTO> getLeaseById(Long id);
    LeaseDTO createLease(LeaseDTO leaseDTO);
    LeaseDTO updateLease(Long id, LeaseDTO leaseDTO);
    void deleteLease(Long id);
}