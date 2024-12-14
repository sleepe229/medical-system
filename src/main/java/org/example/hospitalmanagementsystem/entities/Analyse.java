package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analyzes")
public class Analyse extends BaseEntity {
    Appointment appointment;
    String comment;
    LocalDateTime lastChange;

    protected Analyse() {}
    public Analyse(Appointment appointment, String comment) {
        this.appointment = appointment;
        this.comment = comment;
        this.lastChange = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "appointment_id")
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getLastChange() {
        return lastChange;
    }

    public void setLastChange(LocalDateTime lastChange) {
        this.lastChange = lastChange;
    }
}
