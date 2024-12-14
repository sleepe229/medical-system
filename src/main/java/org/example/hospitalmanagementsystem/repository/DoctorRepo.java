package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.*;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DoctorRepo extends BaseCRUDRepo<Doctor, Integer> {
    Page<Doctor> findByClinicAndSpecialization(Clinic clinic, Specialization specialization, Pageable pageable);
    Page<Doctor> findByFullName(String fullName, Pageable pageable);
    Optional<Doctor> findByUserUsername(String username);
}
