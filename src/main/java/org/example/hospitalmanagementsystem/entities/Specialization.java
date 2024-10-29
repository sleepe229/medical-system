package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specializations")
public class Specialization extends BaseEntity {
    private String specializationName;
    private Set<Doctor> doctors;

    protected Specialization() {}

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "doctor_specialization",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}
