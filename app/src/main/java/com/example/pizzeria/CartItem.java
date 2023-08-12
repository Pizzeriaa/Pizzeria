package com.example.pizzeria;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Pizza pizza;
    private String size;
    private int quantity;

    public CartItem(Pizza pizza, String size, int quantity) {
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return pizza.getPrice() * quantity;
    }
}
