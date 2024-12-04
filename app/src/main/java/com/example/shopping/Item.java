package com.example.shopping;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private double price;
    private int imageResource;

    public Item(String name, double price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
