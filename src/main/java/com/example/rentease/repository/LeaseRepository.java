package com.example.rentease.repository;

import com.example.rentease.model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {
}