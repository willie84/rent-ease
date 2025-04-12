package com.example.rentease.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentease.dto.LeaseDTO;
import com.example.rentease.model.Lease;
import com.example.rentease.repository.LeaseRepository;
import com.example.rentease.service.LeaseService;

@Service
public class LeaseServiceImpl implements LeaseService {
   private final LeaseRepository leaseRepository;

   @Autowired
   public LeaseServiceImpl(LeaseRepository leaseRepository) {
      this.leaseRepository = leaseRepository;
   }

   @Override
   public List<LeaseDTO> getAllLeases() {
      List<Lease> leases = leaseRepository.findAll();
      return leases.stream().map(this::convertToDTO).collect(Collectors.toList());
   }

   @Override
   public Optional<LeaseDTO> getLeaseById(Long id) {
      Optional<Lease> request = leaseRepository.findById(id);
      return request.map(this::convertToDTO);
   }

   @Override
   public LeaseDTO createLease(LeaseDTO leaseDTO) {
      Lease request = convertToEntity(leaseDTO);
      Lease savedRequest = leaseRepository.save(request);
      return convertToDTO(savedRequest);
   }

   @Override
   public LeaseDTO updateLease(Long id, LeaseDTO leaseDTO) {
      Optional<Lease> existingLease = leaseRepository.findById(id);
      if (existingLease.isPresent()) {
         Lease lease = convertToEntity(leaseDTO);
         lease.setId(id);
         Lease updatedLease = leaseRepository.save(lease);
         return convertToDTO(updatedLease);
      } else {
         return null;
      }
   }

   @Override
   public void deleteLease(Long id) {
      leaseRepository.deleteById(id);
   }

   private LeaseDTO convertToDTO(Lease lease) {
      LeaseDTO leaseDTO = new LeaseDTO();
      leaseDTO.setId(lease.getId());
      leaseDTO.setUnitId(lease.getUnitId());
      leaseDTO.setTenantId(lease.getTenantId());
      leaseDTO.setStartDate(lease.getStartDate());
      leaseDTO.setEndDate(lease.getEndDate());
      leaseDTO.setRentAmount(lease.getRentAmount());
      return leaseDTO;
   }

   private Lease convertToEntity(LeaseDTO leaseDTO) {
      Lease lease = new Lease();
      lease.setId(leaseDTO.getId());
      lease.setUnitId(leaseDTO.getUnitId());
      lease.setTenantId(leaseDTO.getTenantId());
      lease.setStartDate(leaseDTO.getStartDate());
      lease.setEndDate(leaseDTO.getEndDate());
      lease.setRentAmount(leaseDTO.getRentAmount());
      return lease;
   }
}