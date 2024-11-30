package org.example.hospitalmanagementsystem.dto;

public class NewsInputDto{
        String title;
        String description;

    public NewsInputDto(String title, String description) {
        this.title = title;
        this.description = description;
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

