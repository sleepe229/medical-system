package org.example.hospitalmanagementsystem.dto;

public record DoctorEditDto(
        String fullName,
        String phoneNumber,
        String position,
        String experienceYears,
        String achievements,
        String education
) {
}
