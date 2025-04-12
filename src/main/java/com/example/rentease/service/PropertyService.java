package com.example.rentease.service;

import com.example.rentease.dto.PropertyDTO;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    List<PropertyDTO> getAllProperties();
    Optional<PropertyDTO> getPropertyById(Long id);
    PropertyDTO createProperty(PropertyDTO propertyDTO);
    PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO);
    void deleteProperty(Long id);
}
