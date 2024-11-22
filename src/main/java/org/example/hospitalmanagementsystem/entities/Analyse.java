package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analyzes")
public class Analyse extends BaseEntity {
    Client client;
    Doctor doctor;
    Status status;
    String comment;
    LocalDateTime dateTaken;
    LocalDateTime lastChange;

    protected Analyse() {}
    protected Analyse(Client client, Status status, String comment) {
        this.client = client;
        this.status = status;
        this.comment = comment;
        this.dateTaken = LocalDateTime.now();
        this.lastChange = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }
}
