package org.example.hospitalmanagementsystem.dto;

public class AppointmentDto{
    Integer id;
    String phoneNumber;
    String direction;
    String clinic;
    String doctorFullName;
    String date;
    String result;
    String status;

    protected AppointmentDto() {
    }

    public AppointmentDto(Integer id, String phoneNumber, String direction, String clinic, String doctorFullName, String date, String result, String status) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.direction = direction;
        this.clinic = clinic;
        this.doctorFullName = doctorFullName;
        this.date = date;
        this.result = result;
        this.status = status;
    }

    public AppointmentDto(Integer id, String phoneNumber, String date, String result, String status) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.result = result;
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppointmentDto{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", direction='" + direction + '\'' +
                ", clinic='" + clinic + '\'' +
                ", doctorFullName='" + doctorFullName + '\'' +
                ", date='" + date + '\'' +
                ", result='" + result + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
