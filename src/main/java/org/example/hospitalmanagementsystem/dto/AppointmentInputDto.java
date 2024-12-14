package org.example.hospitalmanagementsystem.dto;

import java.time.LocalDate;

public class AppointmentInputDto{
    String phoneNumber;
    Integer specializationId;
    Integer clinicId;
    Integer doctorId;
    String date;

    protected AppointmentInputDto() {
    }

    public AppointmentInputDto(String phoneNumber, Integer specializationId, Integer clinicId, Integer doctorId, String date) {
        this.phoneNumber = phoneNumber;
        this.specializationId = specializationId;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
        this.date = date;
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

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AppointmentInputDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", specializationId=" + specializationId +
                ", clinicId=" + clinicId +
                ", doctorId=" + doctorId +
                '}';
    }
}

