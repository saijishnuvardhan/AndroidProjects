package com.example.praneeth.shoping_cart;

public class Buy {

    String username;
    int quantity;
    String model;
    String invoiceNumber;

    public Buy(String username, int quantity, String model, String inovice) {
        this.username = username;
        this.quantity = quantity;
        this.model = model;
        this.invoiceNumber = inovice;
    }

    public String getUsername() {
        return username;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getModel() {
        return model;
    }

    public String getInovice() {
        return invoiceNumber;
    }
}
