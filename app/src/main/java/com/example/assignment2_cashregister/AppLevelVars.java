package com.example.assignment2_cashregister;

import android.app.Application;

import java.util.ArrayList;

public class AppLevelVars extends Application {
    ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        if(products == null) {
            products = new ArrayList<>(4);
            products.add(new Product("Pants",10,39.99));
            products.add(new Product("Shoes",100,99.99));
            products.add(new Product("Hat",25,26.49));
            products.add(new Product("Shirt",18,30.99));
        }
        return products;
    }

    ArrayList<PurchaseHistory> purchaseHistories;
    public ArrayList<PurchaseHistory> getPurchaseHistories(){
        if(purchaseHistories == null) {
            purchaseHistories = new ArrayList<>(0);
        }
        return purchaseHistories;
    }
}
