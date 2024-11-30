package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasePerson extends BaseEntity {
    private String fullName;
    private String phoneNumber;

    protected BasePerson() {}

    public BasePerson(String name, String phoneNumber) {
        this.fullName = name;
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
