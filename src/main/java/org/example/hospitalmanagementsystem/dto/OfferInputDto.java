package org.example.hospitalmanagementsystem.dto;

public class OfferInputDto{
        String title;
        String description;
    Integer price;

    public OfferInputDto(String title, String description, Integer price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public OfferInputDto() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
