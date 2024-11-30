package org.example.hospitalmanagementsystem.dto;

import java.time.LocalDate;

public class DoctorEditDto {
    Integer id;
    String fullName;
    String phoneNumber;
    Integer specializationId;
    String education;
    String hireDate;
    String position;
    Integer statusId;
    Integer clinicId;

    public DoctorEditDto() {
    }

    public DoctorEditDto(Integer id, String fullName, String phoneNumber, Integer specializationId, String education, String hireDate, String position, Integer statusId, Integer clinicId) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.specializationId = specializationId;
        this.education = education;
        this.hireDate = hireDate;
        this.position = position;
        this.statusId = statusId;
        this.clinicId = clinicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }
}
