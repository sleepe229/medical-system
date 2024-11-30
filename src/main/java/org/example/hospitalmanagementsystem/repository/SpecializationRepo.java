package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;

public interface SpecializationRepo extends BaseCRUDRepo<Specialization, Integer> {
    Specialization findBySpecializationName(String name);
}
