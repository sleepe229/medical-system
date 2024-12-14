package org.example.hospitalmanagementsystem.dto;

public class AppointmentEditDto{
    Integer id;
    String phoneNumber;
    String direction;
    String clinic;
    String doctorFullName;
    String result;

    protected AppointmentEditDto() {
    }

    public AppointmentEditDto(Integer id, String phoneNumber, String direction, String clinic, String doctorFullName, String result) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.direction = direction;
        this.clinic = clinic;
        this.doctorFullName = doctorFullName;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setAppointmentId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
