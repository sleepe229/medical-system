package org.example.hospitalmanagementsystem.dto;

public class OfferEditDto{
        Integer id;
        String title;
        String description;
        Integer price;

    public OfferEditDto(
            //Integer id,
            String title, String description, Integer price) {
//        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public OfferEditDto() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
