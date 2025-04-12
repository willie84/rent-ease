package com.example.rentease.service.impl;

import com.example.rentease.dto.TenantDTO;
import com.example.rentease.model.Tenant;
import com.example.rentease.repository.TenantRepository;
import com.example.rentease.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<TenantDTO> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TenantDTO> getTenantById(Long id) {
        Optional<Tenant> tenant = tenantRepository.findById(id);
        return tenant.map(this::convertToDTO);
    }

    @Override
    public TenantDTO createTenant(TenantDTO tenantDTO) {
        Tenant tenant = convertToEntity(tenantDTO);
        Tenant savedTenant = tenantRepository.save(tenant);
        return convertToDTO(savedTenant);
    }

    @Override
    public TenantDTO updateTenant(Long id, TenantDTO tenantDTO) {
        Optional<Tenant> existingTenant = tenantRepository.findById(id);
        if (existingTenant.isPresent()) {
            Tenant tenant = convertToEntity(tenantDTO);
            tenant.setId(id);
            Tenant updatedTenant = tenantRepository.save(tenant);
            return convertToDTO(updatedTenant);
        } else {
            return null;
        }
    }

    @Override
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

    private TenantDTO convertToDTO(Tenant tenant) {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setId(tenant.getId());
        tenantDTO.setName(tenant.getName());
        tenantDTO.setContactInfo(tenant.getContactInfo());
        return tenantDTO;
    }

    private Tenant convertToEntity(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setId(tenantDTO.getId());
        tenant.setName(tenantDTO.getName());
        tenant.setContactInfo(tenantDTO.getContactInfo());
        return tenant;
    }
}
