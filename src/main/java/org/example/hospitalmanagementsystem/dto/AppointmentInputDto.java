package org.example.hospitalmanagementsystem.dto;

public class AppointmentInputDto{
    String phoneNumber;
    Integer specializationId;
    Integer clinicId;
    Integer doctorId;

    protected AppointmentInputDto() {
    }

    public AppointmentInputDto(String phoneNumber, Integer specializationId, Integer clinicId, Integer doctorId) {
        this.phoneNumber = phoneNumber;
        this.specializationId = specializationId;
        this.clinicId = clinicId;
        this.doctorId = doctorId;
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

