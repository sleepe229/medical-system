package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.*;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

import java.util.List;

public interface AppointmentRepo extends CreateRepo<Appointment>, ReadRepo<Appointment>, UpdateRepo<Appointment>{
    List<Appointment> findByClient(Client client);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByClinic(Clinic clinic);
}

