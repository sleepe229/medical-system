package org.example.hospitalmanagementsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "statuses")
public class Status extends BaseEntity {
    private String type;
    private String value;

    protected Status() {}

    public Status(String type, String value) {
        this.type = type;
        this.value = value;
    }
    public String getType() {return this.type;}
    public String getValue() {return this.value;}
    public void setType(String type) {this.type = type;}
    public void setValue(String value) {this.value = value;}
}
