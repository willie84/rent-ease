package com.example.rentease.controller;

import com.example.rentease.dto.UnitDTO;
import com.example.rentease.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/units")
public class UnitController {
    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDTO>> getAllUnits() {
        List<UnitDTO> units = unitService.getAllUnits();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable Long id) {
        Optional<UnitDTO> unit = unitService.getUnitById(id);
        if (unit.isPresent()) {
            return new ResponseEntity<>(unit.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnit(@RequestBody UnitDTO unitDTO) {
        UnitDTO createdUnit = unitService.createUnit(unitDTO);
        return new ResponseEntity<>(createdUnit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable Long id, @RequestBody UnitDTO unitDTO) {
        UnitDTO updatedUnit = unitService.updateUnit(id, unitDTO);
        if (updatedUnit != null) {
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
