package org.example.hospitalmanagementsystem.dto;

public class NewsEditDto{
    Integer id;
    String title;
    String description;
    String publicationDate;

    public NewsEditDto(Integer id, String title, String description, String publicationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
    }

    public NewsEditDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "NewsEditDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}

