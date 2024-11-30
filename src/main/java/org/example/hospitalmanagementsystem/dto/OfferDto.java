package org.example.hospitalmanagementsystem.dto;

public class OfferDto{
        String title;
        String description;
        Integer price;

    public OfferDto(String title, String description, Integer price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public OfferDto() {
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

    @Override
    public String toString() {
        return "OfferDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
