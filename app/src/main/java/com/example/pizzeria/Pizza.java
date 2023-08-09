package com.example.pizzeria;

public class Pizza {
    private String name;
    private int imageResourceId;

    public Pizza(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
