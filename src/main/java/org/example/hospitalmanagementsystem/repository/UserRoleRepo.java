package org.example.hospitalmanagementsystem.repository;

import org.example.hospitalmanagementsystem.entities.Role;
import org.example.hospitalmanagementsystem.entities.enums.UserRoles;
import org.example.hospitalmanagementsystem.repository.base.BaseCRUDRepo;

import java.util.Optional;

public interface UserRoleRepo extends BaseCRUDRepo<Role, Integer> {
    Optional<Role> findRoleByName(UserRoles role);
}
