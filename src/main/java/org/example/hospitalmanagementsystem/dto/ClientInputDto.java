package org.example.hospitalmanagementsystem.dto;

public class ClientInputDto {
    String phoneNumber;
    String fullName;
    String email;

    public ClientInputDto() {
    }

    public ClientInputDto(String phoneNumber, String fullName, String email) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
