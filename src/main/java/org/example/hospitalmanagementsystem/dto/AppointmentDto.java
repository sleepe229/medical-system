package org.example.hospitalmanagementsystem.dto;

public record AppointmentDto(
        String phoneNumber,
        String direction,
        String clinic,
        String doctorFullName,
        String date,
        String status
) {
}
