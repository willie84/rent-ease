package com.example.rentease.service;

import com.example.rentease.dto.TenantDTO;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    List<TenantDTO> getAllTenants();
    Optional<TenantDTO> getTenantById(Long id);
    TenantDTO createTenant(TenantDTO tenantDTO);
    TenantDTO updateTenant(Long id, TenantDTO tenantDTO);
    void deleteTenant(Long id);
}