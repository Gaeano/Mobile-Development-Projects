package com.example.studentloginsystem;

public interface CartItemListener {

    void AddOnClick(Product item, int position);
    void MinusOnClick(Product item, int position);
}
