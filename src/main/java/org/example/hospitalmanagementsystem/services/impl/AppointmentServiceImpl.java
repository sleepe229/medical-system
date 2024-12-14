package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.*;
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
import java.util.Optional;

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

//    @Override
//    public Page<AppointmentDto> getAppointmentsForClient(int clientId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Client client = clientRepo.findById(clientId)
//                .orElseThrow(() -> new IllegalArgumentException("Client with id" + clientId + " not found"));
//        Page<Appointment> appointments = appointmentRepo.findByClient(client, pageable);
//        return appointments.map(appointment -> modelMapper.map(appointment, AppointmentDto.class));
//    }

    @Override
    public void updateAppointmentResult(AppointmentEditDto editDto) {
        Appointment appointment = appointmentRepo.findById(editDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + editDto.getId() + " not found"));

        Analyse analyse = analyseRepo.findByAppointment(appointment);

        if (analyse == null) {
            analyse = new Analyse(appointment, editDto.getResult());

            analyseRepo.save(analyse);
            appointment.setAnalyse(analyse);
        } else {
            analyse.setComment(editDto.getResult());
            analyse.setLastChange(LocalDateTime.now());
            analyseRepo.save(analyse);
        }

//        Status completedStatus = statusRepo.findByValue("COMPLETED");
//        appointment.setStatus(completedStatus);

        appointmentRepo.save(appointment);
    }
    @Override
    public AppointmentDto getAppointmentById(Integer appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).get();
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    @Override
    public Page<AppointmentDto> getAppointmentsForClient(String username, int page, int size) {
        var client = clientRepo.findByUserUsername(username).get();

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Appointment> appointments = appointmentRepo.findByClient(client, pageable);
        return appointments.map(appointment -> modelMapper.map(appointment, AppointmentDto.class));
    }

    @Override
    public Page<AppointmentDto> getAppointmentsForDoctor(String username, int page, int size) {
        var doctor = doctorRepo.findByUserUsername(username).get();

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Appointment> appointmentPage = appointmentRepo.findByDoctor(doctor, pageable);
        return appointmentPage.map(appointment -> new AppointmentDto(appointment.getId(), appointment.getClient().getPhoneNumber(), appointment.getAppointmentDate().toString(), appointment.getAnalyse() != null ? appointment.getAnalyse().getComment() : null, appointment.getStatus().getValue()));

    }
}
