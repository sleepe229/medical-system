package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
 import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

import java.util.List;

public interface DoctorRepo extends CreateRepo<Doctor>, ReadRepo<Doctor>, UpdateRepo<Doctor> {
    List<Doctor> findByClinicIdAndSpecializationId(Clinic clinic, Specialization specialization);
}
