package com.example.rentease.service;
import com.example.rentease.dto.UnitDTO;

import java.util.List;
import java.util.Optional;
public interface UnitService {
    List<UnitDTO> getAllUnits();
    Optional<UnitDTO> getUnitById(Long id);
    UnitDTO createUnit(UnitDTO unitDTO);
    UnitDTO updateUnit(Long id, UnitDTO unitDTO);
    void deleteUnit(Long id);
}
