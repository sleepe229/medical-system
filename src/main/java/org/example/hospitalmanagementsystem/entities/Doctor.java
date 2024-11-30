package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "doctors")
public class Doctor extends BasePerson {
    private Specialization specialization;
    private String education;
    private LocalDate hireDate;
    private String position;
    private Status status;
    private Clinic clinic;

    protected Doctor() {}

    public Doctor(String name, String phoneNumber, Specialization specialization, String education, LocalDate hireDate, String position, Status status, Clinic clinic) {
        super(name, phoneNumber);
        this.specialization = specialization;
        this.education = education;
        this.hireDate = hireDate;
        this.position = position;
        this.status = status;
        this.clinic = clinic;
    }

    @ManyToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
