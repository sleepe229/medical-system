package org.example.hospitalmanagementsystem.services;


import org.example.hospitalmanagementsystem.dto.AppointmentDto;
import org.example.hospitalmanagementsystem.dto.AppointmentEditDto;
import org.example.hospitalmanagementsystem.dto.AppointmentInputDto;
import org.example.hospitalmanagementsystem.entities.Appointment;
import org.springframework.data.domain.Page;

public interface AppointmentService {
    void createAppointment(AppointmentInputDto inputDto);
    void updateAppointmentResult(AppointmentEditDto editDto);
    AppointmentDto getAppointmentById(Integer appointmentId);
//    Page<AppointmentDto> getAppointmentsForClient(int clientId, int page, int size);
    Page<AppointmentDto> getAppointmentsForClient(String username, int page, int size);
//    Page<AppointmentDto> getAppointmentsForDoctor(int doctorId, int page, int size);
    Page<AppointmentDto> getAppointmentsForDoctor(String username, int page, int size);
}

