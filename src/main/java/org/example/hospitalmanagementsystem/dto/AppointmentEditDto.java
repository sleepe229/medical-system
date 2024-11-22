package org.example.hospitalmanagementsystem.dto;

public record AppointmentEditDto(
        String phoneNumber,
        String direction,
        String clinic,
        String doctorFullName,
        String result
) {
}
