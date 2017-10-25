package com.example.etix42.miniprojet4;

import java.io.Serializable;

public class seisme implements Serializable {

    private String title;
    private String description;

    public seisme(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
