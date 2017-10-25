package com.example.etix42.miniprojet4;

import java.io.Serializable;
import java.io.StreamCorruptedException;

public class seisme implements Serializable {

    private String title;
    private String description;
    private String updated;
    private String coord;

    public seisme(String title, String description, String updated, String coord) {
        this.title = title;
        this.description = description;
        this.updated = updated;
        this.coord = coord;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCoord() {
        return coord;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }
}
