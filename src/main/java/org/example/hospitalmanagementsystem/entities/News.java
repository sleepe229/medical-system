package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News extends BaseEntity {
    private String title;
    private String description;
    private String publicationDate;

    protected News() {}

    public News(String title, String description) {
        this.title = title;
        this.description = description;
        this.publicationDate = LocalDateTime.now().toString();
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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}

