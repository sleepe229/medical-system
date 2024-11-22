package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

public interface SpecializationRepo extends CreateRepo<Specialization>, ReadRepo<Specialization>, UpdateRepo<Specialization>{
    Specialization findByName(String name);
}
