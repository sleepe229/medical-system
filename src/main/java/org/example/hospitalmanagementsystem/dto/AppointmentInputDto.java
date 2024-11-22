package org.example.hospitalmanagementsystem.dto;

public record AppointmentInputDto(
        String phoneNumber,
        String direction,
        String clinic,
        String doctorFullName
) {
}
