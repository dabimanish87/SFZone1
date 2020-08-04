package com.example.sfzone;

public class GruhUdhyogDetails {
    private String name;
    private String id,price,qty;

    public GruhUdhyogDetails(String name, String id, String price, String qty) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return String.valueOf(qty);
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
