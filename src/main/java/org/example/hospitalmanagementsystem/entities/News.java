package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News extends BaseEntity {
    private String title;
    private String description;
    private LocalDateTime publicationDate;

    protected News() {}

    public News(String title, String description) {
        this.title = title;
        this.description = description;
        this.publicationDate = LocalDateTime.now();
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "publication_date")
    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }
}

