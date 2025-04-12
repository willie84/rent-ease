package com.example.rentease.service.impl;

import com.example.rentease.dto.MaintenanceRequestDTO;
import com.example.rentease.model.MaintenanceRequest;
import com.example.rentease.repository.MaintenanceRequestRepository;
import com.example.rentease.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRequestRepository maintenanceRequestRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRequestRepository maintenanceRequestRepository) {
        this.maintenanceRequestRepository = maintenanceRequestRepository;
    }

    @Override
    public List<MaintenanceRequestDTO> getAllMaintenanceRequests() {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findAll();
        return maintenanceRequests.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MaintenanceRequestDTO> getMaintenanceRequestById(Long id) {
        Optional<MaintenanceRequest> request = maintenanceRequestRepository.findById(id);
        return request.map(this::convertToDTO);
    }

    @Override
    public MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequest request = convertToEntity(maintenanceRequestDTO);
        MaintenanceRequest savedRequest = maintenanceRequestRepository.save(request);
        return convertToDTO(savedRequest);
    }

    @Override
    public MaintenanceRequestDTO updateMaintenanceRequest(Long id, MaintenanceRequestDTO maintenanceRequestDTO) {
        Optional<MaintenanceRequest> existingRequest = maintenanceRequestRepository.findById(id);
        if (existingRequest.isPresent()) {
            MaintenanceRequest request = convertToEntity(maintenanceRequestDTO);
            request.setId(id);
            MaintenanceRequest updatedRequest = maintenanceRequestRepository.save(request);
            return convertToDTO(updatedRequest);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMaintenanceRequest(Long id) {
        maintenanceRequestRepository.deleteById(id);
    }

    private MaintenanceRequestDTO convertToDTO(MaintenanceRequest request) {
        MaintenanceRequestDTO requestDTO = new MaintenanceRequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setUnitId(request.getUnitId());
        requestDTO.setDescription(request.getDescription());
        requestDTO.setRequestDate(request.getRequestDate());
        requestDTO.setStatus(request.getStatus());
        return requestDTO;
    }

    private MaintenanceRequest convertToEntity(MaintenanceRequestDTO requestDTO) {
        MaintenanceRequest request = new MaintenanceRequest();
        request.setId(requestDTO.getId());
        request.setUnitId(requestDTO.getUnitId());
        request.setDescription(requestDTO.getDescription());
        request.setRequestDate(requestDTO.getRequestDate());
        request.setStatus(requestDTO.getStatus());
        return request;
    }
}