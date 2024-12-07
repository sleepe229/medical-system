package org.example.hospitalmanagementsystem.dto;

public class OfferDto{
        String title;
        String description;
        Integer price;
        String imageUrl;

    public OfferDto(String title, String description, Integer price, String imageUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
