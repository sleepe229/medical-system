package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Client;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

public interface ClientRepo extends CreateRepo<Client>, ReadRepo<Client>, UpdateRepo<Client> {
}
