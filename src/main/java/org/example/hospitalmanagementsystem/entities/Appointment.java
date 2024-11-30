package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
    Doctor doctor;
    Client client;
    LocalDateTime appointmentDate;
    Status status;
    Analyse analyse;

    protected Appointment() {}
    public Appointment(Doctor doctor, Client client, LocalDateTime appointmentDate, Status status) {
        this.doctor = doctor;
        this.client = client;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @OneToOne
    @JoinColumn(name = "analyse_id")
    public Analyse getAnalyse() {
        return analyse;
    }

    public void setAnalyse(Analyse analyse) {
        this.analyse = analyse;
    }
}
