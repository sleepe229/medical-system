package org.example.hospitalmanagementsystem.dto;

public class DoctorDto{
        Integer id;
        String fullName;
        String position;
        String specialization;
        Integer experienceYears;

    public DoctorDto(int id, String fullName, String education, String position, String specializationName, int i) {
    }

    public DoctorDto(Integer id, String fullName, String position, String specialization, Integer experienceYears) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
