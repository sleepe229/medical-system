package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;
import java.util.List;

public interface ClinicRepo extends CreateRepo<Clinic>, ReadRepo<Clinic>, UpdateRepo<Clinic>{
    List<Clinic> findBySpecialization(Specialization specialization);
}
