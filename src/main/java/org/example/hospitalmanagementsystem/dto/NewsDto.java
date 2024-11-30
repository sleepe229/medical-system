package org.example.hospitalmanagementsystem.dto;

import java.beans.ConstructorProperties;

public class NewsDto{
        String title;
        String description;
        String publicationDate;

        public NewsDto() {
        }

        public NewsDto(String title, String description, String publicationDate) {
                this.title = title;
                this.description = description;
                this.publicationDate = publicationDate;
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
                return "NewsDto{" +
                        "title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", publicationDate='" + publicationDate + '\'' +
                        '}';
        }
}
