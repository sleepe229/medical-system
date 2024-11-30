package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClinicRepo extends BaseCRUDRepo<Clinic, Integer> {
    @Query("SELECT DISTINCT c FROM Clinic c JOIN c.doctors d WHERE d.specialization = :specialization")
    List<Clinic> findBySpecialization(@Param("specialization") Specialization specialization);
}


