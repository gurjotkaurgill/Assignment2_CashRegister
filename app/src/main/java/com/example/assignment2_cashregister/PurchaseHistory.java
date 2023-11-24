package com.example.assignment2_cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PurchaseHistory implements Parcelable {
    private final String purchaseProductName;
    private final int purchaseQuantity;
    private final double purchasePrice;
    private final String purchaseDate;

    public PurchaseHistory(String purchaseProductName, int purchaseQuantity, double purchasePrice, String purchaseDate) {
        this.purchaseProductName = purchaseProductName;
        this.purchaseQuantity = purchaseQuantity;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
    }

    protected PurchaseHistory(Parcel in) {
        purchaseProductName = in.readString();
        purchaseQuantity = in.readInt();
        purchasePrice = in.readDouble();
        purchaseDate = in.readString();
    }

    public static final Creator<PurchaseHistory> CREATOR = new Creator<PurchaseHistory>() {
        @Override
        public PurchaseHistory createFromParcel(Parcel in) {
            return new PurchaseHistory(in);
        }

        @Override
        public PurchaseHistory[] newArray(int size) {
            return new PurchaseHistory[size];
        }
    };

    public String getPurchaseProductName() {
        return purchaseProductName;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(purchaseProductName);
        dest.writeInt(purchaseQuantity);
        dest.writeDouble(purchasePrice);
        dest.writeString(purchaseDate);
    }

    @NonNull
    @Override
    public String toString() {
        return "Product: " + purchaseProductName + "\n" +
                "Purchase Quantity: " + purchaseQuantity + "\n" +
                "Price: $" + purchasePrice + "\n" +
                "Purchase Date:\n" + purchaseDate;
    }
}
