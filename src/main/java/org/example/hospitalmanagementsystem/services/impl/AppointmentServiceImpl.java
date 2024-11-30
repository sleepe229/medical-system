package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.AppointmentDto;
import org.example.hospitalmanagementsystem.dto.AppointmentEditDto;
import org.example.hospitalmanagementsystem.dto.AppointmentInputDto;
import org.example.hospitalmanagementsystem.entities.*;
import org.example.hospitalmanagementsystem.repository.*;
import org.example.hospitalmanagementsystem.services.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final ClinicRepo clinicRepo;
    private final SpecializationRepo specializationRepo;
    private final DoctorRepo doctorRepo;
    private final ClientRepo clientRepo;
    private final StatusRepo statusRepo;
    private final ModelMapper modelMapper;
    private final AnalyseRepo analyseRepo;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, ClinicRepo clinicRepo, SpecializationRepo specializationRepo, DoctorRepo doctorRepo, ClientRepo clientRepo, StatusRepo statusRepo, ModelMapper modelMapper, AnalyseRepo analyseRepo) {
        this.appointmentRepo = appointmentRepo;
        this.clinicRepo = clinicRepo;
        this.specializationRepo = specializationRepo;
        this.doctorRepo = doctorRepo;
        this.clientRepo = clientRepo;
        this.statusRepo = statusRepo;
        this.modelMapper = modelMapper;
        this.analyseRepo = analyseRepo;
    }

    @Override
    public void createAppointment(AppointmentInputDto inputDto) {
        Clinic clinic = clinicRepo.findById(inputDto.getClinicId())
                .orElseThrow(() -> new IllegalArgumentException("Clinic with ID " + inputDto.getClinicId() + " not found"));

        Specialization specialization = specializationRepo.findById(inputDto.getSpecializationId())
                .orElseThrow(() -> new IllegalArgumentException("Specialization '" + inputDto.getSpecializationId() + "' not found"));

        Doctor doctor = doctorRepo.findById(inputDto.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor with ID " + inputDto.getDoctorId() + " not found"));

        Client client = clientRepo.findByPhoneNumber(inputDto.getPhoneNumber())
                .orElseThrow(() -> new IllegalArgumentException("Client with phone number " + inputDto.getPhoneNumber() + " not found"));

        Status scheduledStatus = statusRepo.findByValue("SCHEDULED");
        Appointment appointment = new Appointment(doctor, client, LocalDateTime.now(), scheduledStatus);
        appointmentRepo.save(appointment);
    }


    @Override
    public Page<AppointmentDto> getAppointmentsForClient(String clientPhoneNumber, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Client client = clientRepo.findByPhoneNumber(clientPhoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Client with phone number " + clientPhoneNumber + " not found"));
        Page<Appointment> appointments = appointmentRepo.findByClient(client, pageable);
        return appointments.map(appointment -> modelMapper.map(appointment, AppointmentDto.class));
    }

    @Override
    public void updateAppointmentResult(AppointmentEditDto editDto) {
        Appointment appointment = appointmentRepo.findById(editDto.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + editDto.getAppointmentId() + " not found"));

        Analyse analyse = analyseRepo.findByAppointment(appointment);
        analyse.setComment(editDto.getResult());
        analyse.setLastChange(LocalDateTime.now());

        Status completedStatus = statusRepo.findByValue("COMPLETED");
        appointment.setStatus(completedStatus);

        appointmentRepo.save(appointment);
    }

    @Override
    public Page<AppointmentDto> getAppointmentsForDoctor(String doctorName, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Doctor doctor = doctorRepo.findByFullName(doctorName)
                .orElseThrow(() -> new IllegalArgumentException("Doctor with name '" + doctorName + "' not found"));

        Page<Appointment> appointments = appointmentRepo.findByDoctor(doctor, pageable);
        return appointments.map(appointment -> modelMapper.map(appointment, AppointmentDto.class));
    }

}
