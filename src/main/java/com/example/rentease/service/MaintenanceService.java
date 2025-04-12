package com.example.rentease.service;

import com.example.rentease.dto.MaintenanceRequestDTO;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {
    List<MaintenanceRequestDTO> getAllMaintenanceRequests();
    Optional<MaintenanceRequestDTO> getMaintenanceRequestById(Long id);
    MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO maintenanceRequestDTO);
    MaintenanceRequestDTO updateMaintenanceRequest(Long id, MaintenanceRequestDTO maintenanceRequestDTO);
    void deleteMaintenanceRequest(Long id);
}