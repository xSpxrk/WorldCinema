package com.example.cinemaworld.pages.collections;

public class Collection {
    private int image;
    private String brand;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public Collection(int image, String brand) {
        this.image = image;
        this.brand = brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
