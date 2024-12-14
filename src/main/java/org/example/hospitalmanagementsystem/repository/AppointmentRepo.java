package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Appointment;
import org.example.hospitalmanagementsystem.entities.Client;
import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepo extends BaseCRUDRepo<Appointment, Integer> {
    Page<Appointment> findByClient(Client client, Pageable pageable);
    Page<Appointment> findByDoctor(Doctor doctor, Pageable pageable);
}
