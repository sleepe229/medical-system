package org.example.hospitalmanagementsystem.entities;

public class Statuses extends BaseEntity {
    private String type;
    private String value;

    protected Statuses() {}

    public Statuses(String type, String value) {

    }
    private String getType() {return this.type;}
    private String getValue() {return this.value;}
    public void setType(String type) {this.type = type;}
    public void setValue(String value) {this.value = value;}
}
