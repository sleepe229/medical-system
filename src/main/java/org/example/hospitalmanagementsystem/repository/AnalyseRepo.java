package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Analyse;
import org.example.hospitalmanagementsystem.entities.Appointment;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;

public interface AnalyseRepo extends BaseCRUDRepo<Analyse, Integer> {
    Analyse findByAppointment(Appointment appointment);
}
