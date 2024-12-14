package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.*;
import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.entities.Doctor;
import org.example.hospitalmanagementsystem.entities.Specialization;
import org.example.hospitalmanagementsystem.repository.ClinicRepo;
import org.example.hospitalmanagementsystem.repository.DoctorRepo;
import org.example.hospitalmanagementsystem.repository.SpecializationRepo;
import org.example.hospitalmanagementsystem.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepo doctorRepo;
    private final ClinicRepo clinicRepo;
    private final SpecializationRepo specializationRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, ClinicRepo clinicRepo, SpecializationRepo specializationRepo, ModelMapper modelMapper) {
        this.doctorRepo = doctorRepo;
        this.clinicRepo = clinicRepo;
        this.specializationRepo = specializationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDoctor(DoctorInputDto doctorInputDto) {
        Optional<Clinic> optionalClinic = clinicRepo.findById(doctorInputDto.getClinicId());
        if (optionalClinic.isEmpty()) {
            throw new IllegalArgumentException("Clinic with ID " + doctorInputDto.getClinicId() + " not found");
        }
        Clinic clinic = optionalClinic.get();

        Optional<Specialization> optionalSpecialization = specializationRepo.findById(doctorInputDto.getSpecializationId());
        if (optionalSpecialization.isEmpty()) {
            throw new IllegalArgumentException("Specialization '" + doctorInputDto.getSpecializationId() +" not found");
        }
        Specialization specialization = optionalSpecialization.get();

        Doctor doctor = modelMapper.map(doctorInputDto, Doctor.class);
        doctor.setClinic(clinic);
        doctor.setSpecialization(specialization);

        doctorRepo.save(doctor);
    }


    @Override
    public void updateDoctor(DoctorEditDto doctorEditDto) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorEditDto.getId());
        if (optionalDoctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor with ID " + doctorEditDto.getId() + " not found");
        }

        Doctor existingDoctor = optionalDoctor.get();

        existingDoctor.setFullName(doctorEditDto.getFullName());
        existingDoctor.setPhoneNumber(doctorEditDto.getPhoneNumber());
        existingDoctor.setPosition(doctorEditDto.getPosition());
        existingDoctor.setEducation(doctorEditDto.getEducation());

        doctorRepo.save(existingDoctor);
    }


    @Override
    public DoctorDto getDoctor(int id) {
        Optional<Doctor> doctor = doctorRepo.findById(id);
        if (doctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor with ID " + id + " not found");
        }
        return modelMapper.map(doctor.get(), DoctorDto.class);
    }

    @Override
    public Page<DoctorDto> getDoctors(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Doctor> doctorPage = (searchTerm != null && !searchTerm.isEmpty())
                ? doctorRepo.findByFullName(searchTerm, pageable)
                : doctorRepo.findAll(pageable);
        return doctorPage.map(doctor -> new DoctorDto(doctor.getId(), doctor.getFullName(), doctor.getEducation(), doctor.getPosition(), doctor.getSpecialization().getSpecializationName(), 1));
    }

    public void configureMappings() {
        modelMapper.addMappings(new PropertyMap<DoctorInputDto, Doctor>() {
            @Override
            protected void configure() {
                map(source.getSpecializationId(), destination.getSpecialization().getId());
                map(source.getClinicId(), destination.getClinic().getId());
                map(source.getStatusId(), destination.getStatus().getId());
            }
        });
    }

}
