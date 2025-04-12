package com.example.rentease.controller;

import com.example.rentease.dto.TenantDTO;
import com.example.rentease.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<TenantDTO>> getAllTenants() {
        List<TenantDTO> tenants = tenantService.getAllTenants();
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantDTO> getTenantById(@PathVariable Long id) {
        Optional<TenantDTO> tenant = tenantService.getTenantById(id);
        if (tenant.isPresent()) {
            return new ResponseEntity<>(tenant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantDTO tenantDTO) {
        TenantDTO createdTenant = tenantService.createTenant(tenantDTO);
        return new ResponseEntity<>(createdTenant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantDTO> updateTenant(@PathVariable Long id, @RequestBody TenantDTO tenantDTO) {
        TenantDTO updatedTenant = tenantService.updateTenant(id, tenantDTO);
        if (updatedTenant != null) {
            return new ResponseEntity<>(updatedTenant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

