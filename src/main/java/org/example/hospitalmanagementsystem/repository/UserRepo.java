package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.User;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseCRUDRepo<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
