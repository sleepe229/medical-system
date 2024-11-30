package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.ClinicDto;
import org.example.hospitalmanagementsystem.dto.ClinicInputDto;
import org.example.hospitalmanagementsystem.dto.ClinicEditDto;
import org.example.hospitalmanagementsystem.entities.Clinic;
import org.example.hospitalmanagementsystem.repository.ClinicRepo;
import org.example.hospitalmanagementsystem.services.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepo clinicRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ClinicServiceImpl(ClinicRepo clinicRepo, ModelMapper modelMapper) {
        this.clinicRepo = clinicRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createClinic(ClinicInputDto clinicInputDto) {
        Clinic clinic = modelMapper.map(clinicInputDto, Clinic.class);
        clinicRepo.save(clinic);
    }

    @Override
    public ClinicDto getClinicById(Integer id) {
        Clinic clinic = clinicRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Клиника с ID " + id + " не найдена"));
        return modelMapper.map(clinic, ClinicDto.class);
    }

    @Override
    public void updateClinic(ClinicEditDto clinicEditDto) {
        Clinic clinic = clinicRepo.findById(clinicEditDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Клиника с ID " + clinicEditDto.getId() + " не найдена"));

        clinic.setName(clinicEditDto.getName());
        clinic.setAddress(clinicEditDto.getAddress());

        clinicRepo.save(clinic);
    }

    @Override
    public Page<ClinicDto> getClinics(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Clinic> clinics = clinicRepo.findAll(pageable);
        return clinics.map(clinic -> modelMapper.map(clinic, ClinicDto.class));
    }
}

