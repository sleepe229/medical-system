package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByPhoneNumber(String phoneNumber);
}

