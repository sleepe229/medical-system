package org.example.hospitalmanagementsystem.services.impl;

import org.example.hospitalmanagementsystem.dto.UserRegistrationDto;
import org.example.hospitalmanagementsystem.entities.User;
import org.example.hospitalmanagementsystem.entities.enums.UserRoles;
import org.example.hospitalmanagementsystem.repository.UserRepo;
import org.example.hospitalmanagementsystem.repository.UserRoleRepo;
import org.example.hospitalmanagementsystem.services.AuthService;
import org.example.hospitalmanagementsystemview.input.UserEditForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepository;

    private final UserRoleRepo userRoleRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepo userRepository, UserRoleRepo userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findRoleByName(UserRoles.USER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getPhoneNumber(),
                registrationDTO.getFullName()
        );

        user.setRoles(List.of(userRole));

        this.userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    public void edit(String username, UserEditForm updatedUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));;
        user.setUsername(updatedUser.fullName());
        user.setEmail(updatedUser.email());
        user.setPassword(updatedUser.password());
        user.setPhoneNumber(updatedUser.phoneNumber());

        userRepository.save(user);
    }
}
