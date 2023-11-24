package com.example.assignment2_cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product {
    private final String name;
    private int quantity;
    private final double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

}
