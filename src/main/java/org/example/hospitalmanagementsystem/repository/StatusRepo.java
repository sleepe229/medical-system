package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Status;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;

public interface StatusRepo extends BaseCRUDRepo<Status, Integer> {
    Status findByValue(String value);
}
