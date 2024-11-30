package org.example.hospitalmanagementsystem.dto;

import java.util.List;


public class DoctorInputDto{
        String fullName;
        String phoneNumber;
        Integer specializationId;
        String education;
        String hireDate;
        String position;
        Integer statusId;
        Integer clinicId;

    public DoctorInputDto() {
    }

    public DoctorInputDto(String fullName, String phoneNumber, Integer specializationId, String education, String hireDate, String position, Integer statusId, Integer clinicId) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.specializationId = specializationId;
        this.education = education;
        this.hireDate = hireDate;
        this.position = position;
        this.statusId = statusId;
        this.clinicId = clinicId;
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