package com.example.studentloginsystem;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int quantity;
    private int imageID;
    private int price;

    public Product(String name, int quantity, int imageID, int price) {
        this.name = name;
        this.quantity = quantity;
        this.imageID = imageID;
        this.price = price;
    }

    void setImageURL(int imageID) {
        this.imageID = imageID;
    }

    int getImageURL() {
        return imageID;
    }

   void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int getQuantity(){
        return quantity;
    }

    void setName(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setPrice (int price){
        this.price = price;
    }

    int getPrice(){
        return price;
    }

}
