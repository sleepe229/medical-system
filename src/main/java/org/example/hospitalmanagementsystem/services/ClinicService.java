package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.ClinicDto;
import org.example.hospitalmanagementsystem.dto.ClinicInputDto;
import org.example.hospitalmanagementsystem.dto.ClinicEditDto;
import org.springframework.data.domain.Page;

public interface ClinicService {
    void createClinic(ClinicInputDto clinicInputDto);
    ClinicDto getClinicById(Integer id);
    void updateClinic(ClinicEditDto clinicEditDto);
    Page<ClinicDto> getClinics(int page, int size);
}
