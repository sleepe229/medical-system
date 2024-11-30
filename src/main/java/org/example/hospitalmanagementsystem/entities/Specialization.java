package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specializations")
public class Specialization extends BaseEntity {
    private String specializationName;

    protected Specialization() {}

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
}
