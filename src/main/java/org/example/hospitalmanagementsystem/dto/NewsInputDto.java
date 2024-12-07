package org.example.hospitalmanagementsystem.dto;

import java.util.Date;

public class NewsInputDto{
        String title;
        String description;
        String publicationDate;

    public NewsInputDto(String title, String description) {
        this.title = title;
        this.description = description;
        this.publicationDate = new Date().toString();
    }

    public NewsInputDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

