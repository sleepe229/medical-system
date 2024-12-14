package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.DoctorDto;
import org.example.hospitalmanagementsystem.dto.DoctorInputDto;
import org.example.hospitalmanagementsystem.dto.DoctorEditDto;
import org.springframework.data.domain.Page;

public interface DoctorService {
    void createDoctor(DoctorInputDto doctorInputDto);
    void updateDoctor(DoctorEditDto doctorEditDto);
    DoctorDto getDoctor(int id);
    Page<DoctorDto> getDoctors(String doctorSearchTerm, int doctorPage, int doctorSize);
}
