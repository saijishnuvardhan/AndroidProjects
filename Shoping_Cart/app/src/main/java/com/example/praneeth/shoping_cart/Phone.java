package com.example.praneeth.shoping_cart;

public class Phone {
    private String model;
    private String manufacturer;
    private int price;
    private String image;

    public Phone(String model, String manufacturer, int price, String image) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
