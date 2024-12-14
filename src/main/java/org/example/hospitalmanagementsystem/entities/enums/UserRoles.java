package org.example.hospitalmanagementsystem.entities.enums;

public enum UserRoles {
    ADMIN("admin"), DOCTOR("doctor"), USER("user");

    private String value;

    UserRoles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

