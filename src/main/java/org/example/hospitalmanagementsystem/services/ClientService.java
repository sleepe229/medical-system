package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.ClientDto;
import org.example.hospitalmanagementsystem.dto.ClientEditDto;
import org.example.hospitalmanagementsystem.dto.ClientInputDto;

public interface ClientService {
    void createClient(ClientInputDto clientInputDto);
    void updateClient(ClientEditDto clientInputDto);
    ClientDto getClientById(String clientId);
}
