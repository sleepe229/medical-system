package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Analyse;
import org.example.hospitalmanagementsystem.entities.Client;
import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

import java.util.List;

public interface AnalyseRepo extends CreateRepo<Analyse>, ReadRepo<Analyse>, UpdateRepo<Analyse> {
    List<Analyse> findByClient(Client client);
    List<Analyse> findByDoctor(Doctor doctor);
}

