package com.example.rentease.service.impl;

import com.example.rentease.dto.UnitDTO;
import com.example.rentease.model.Unit;
import com.example.rentease.repository.UnitRepository;
import com.example.rentease.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;

    @Autowired
    public UnitServiceImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public List<UnitDTO> getAllUnits() {
        List<Unit> units = unitRepository.findAll();
        return units.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UnitDTO> getUnitById(Long id) {
        Optional<Unit> unit = unitRepository.findById(id);
        return unit.map(this::convertToDTO);
    }

    @Override
    public UnitDTO createUnit(UnitDTO unitDTO) {
        Unit unit = convertToEntity(unitDTO);
        Unit savedUnit = unitRepository.save(unit);
        return convertToDTO(savedUnit);
    }

    @Override
    public UnitDTO updateUnit(Long id, UnitDTO unitDTO) {
        Optional<Unit> existingUnit = unitRepository.findById(id);
        if (existingUnit.isPresent()) {
            Unit unit = convertToEntity(unitDTO);
            unit.setId(id);
            Unit updatedUnit = unitRepository.save(unit);
            return convertToDTO(updatedUnit);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUnit(Long id) {
        unitRepository.deleteById(id);
    }

    private UnitDTO convertToDTO(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(unit.getId());
        unitDTO.setPropertyId(unit.getPropertyId());
        unitDTO.setUnitNumber(unit.getUnitNumber());
        unitDTO.setType(unit.getType());
        unitDTO.setRentAmount(unit.getRentAmount());
        return unitDTO;
    }

    private Unit convertToEntity(UnitDTO unitDTO) {
        Unit unit = new Unit();
        unit.setId(unitDTO.getId());
        unit.setPropertyId(unitDTO.getPropertyId());
        unit.setUnitNumber(unitDTO.getUnitNumber());
        unit.setType(unitDTO.getType());
        unit.setRentAmount(unitDTO.getRentAmount());
        return unit;
    }
}