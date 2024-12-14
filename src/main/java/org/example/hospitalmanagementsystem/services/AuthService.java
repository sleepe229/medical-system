package org.example.hospitalmanagementsystem.services;

import org.example.hospitalmanagementsystem.dto.UserRegistrationDto;
import org.example.hospitalmanagementsystem.entities.User;

public interface AuthService {
    void register(UserRegistrationDto registrationDTO);
    User getUser(String username);
}
