package com.example.studentloginsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class productModel  {
    private  String name;
    private double price;
    private String color;

    private String size;

    public productModel(String name, double price, String color, String size){
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
    }


    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(double Price){
        this.price = price;
    }

    public double getPrice (){
        return price;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public void setSize(String size){
        this.size = size;
    }

    public String getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Price: $" + price + "\n" +
                "Color: " + color + "\n" +
                "Size: " + size;
    }


}