package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    private String title;
    private String description;
    private int price;
    private Status status;

    protected Offer() {}
    public Offer(String title, String description, int price, Status status) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "status_id")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
