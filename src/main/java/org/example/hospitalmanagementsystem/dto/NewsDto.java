package org.example.hospitalmanagementsystem.dto;

import java.time.LocalDateTime;

public record NewsDto(
        String title,
        String description,
        String publicationDate

) {
}
