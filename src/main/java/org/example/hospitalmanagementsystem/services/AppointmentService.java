package org.example.hospitalmanagementsystem.services;


import org.example.hospitalmanagementsystem.dto.AppointmentDto;
import org.example.hospitalmanagementsystem.dto.AppointmentEditDto;
import org.example.hospitalmanagementsystem.dto.AppointmentInputDto;
import org.springframework.data.domain.Page;

public interface AppointmentService {
    void createAppointment(AppointmentInputDto inputDto);
    Page<AppointmentDto> getAppointmentsForClient(String clientPhoneNumber, int page, int size);
    void updateAppointmentResult(AppointmentEditDto editDto);
    Page<AppointmentDto> getAppointmentsForDoctor(String doctorName, int page, int size);
}

