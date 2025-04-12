package com.example.rentease.controller;


import com.example.rentease.dto.LeaseDTO;
import com.example.rentease.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {
    private final LeaseService leaseService;

    @Autowired
    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public ResponseEntity<List<LeaseDTO>> getAllLeases() {
        List<LeaseDTO> leases = leaseService.getAllLeases();
        return new ResponseEntity<>(leases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaseDTO> getLeaseById(@PathVariable Long id) {
        Optional<LeaseDTO> lease = leaseService.getLeaseById(id);
        if (lease.isPresent()) {
            return new ResponseEntity<>(lease.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<LeaseDTO> createLease(@RequestBody LeaseDTO leaseDTO) {
        LeaseDTO createdLease = leaseService.createLease(leaseDTO);
        return new ResponseEntity<>(createdLease, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaseDTO> updateLease(@PathVariable Long id, @RequestBody LeaseDTO leaseDTO) {
        LeaseDTO updatedLease = leaseService.updateLease(id, leaseDTO);
        if (updatedLease != null) {
            return new ResponseEntity<>(updatedLease, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLease(@PathVariable Long id) {
        leaseService.deleteLease(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}