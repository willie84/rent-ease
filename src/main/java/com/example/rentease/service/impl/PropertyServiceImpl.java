package com.example.rentease.service.impl;

import com.example.rentease.dto.PropertyDTO;
import com.example.rentease.model.Property;
import com.example.rentease.repository.PropertyRepository;
import com.example.rentease.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PropertyDTO> getPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.map(this::convertToDTO);
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        Property property = convertToEntity(propertyDTO);
        Property savedProperty = propertyRepository.save(property);
        return convertToDTO(savedProperty);
    }

    @Override
    public PropertyDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        Optional<Property> existingProperty = propertyRepository.findById(id);
        if (existingProperty.isPresent()) {
            Property property = convertToEntity(propertyDTO);
            property.setId(id);
            Property updatedProperty = propertyRepository.save(property);
            return convertToDTO(updatedProperty);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    private PropertyDTO convertToDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setName(property.getName());
        propertyDTO.setAddress(property.getAddress());
        return propertyDTO;
    }

    private Property convertToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setId(propertyDTO.getId());
        property.setName(propertyDTO.getName());
        property.setAddress(propertyDTO.getAddress());
        return property;
    }
}