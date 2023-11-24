package com.example.assignment2_cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ProductBaseAdapter extends BaseAdapter {
    private ArrayList<Product> productsList;
    private Context context;

    public ProductBaseAdapter(ArrayList<Product> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.products_row,parent,false);
        TextView productName = v.findViewById(R.id.leftTextView_Row);
        TextView productQty = v.findViewById(R.id.rightTextView_Row);
        TextView productPrice = v.findViewById(R.id.bottomTextView_Row);
        productName.setText(productsList.get(position).getName());
        productQty.setText(String.valueOf(productsList.get(position).getQuantity()));
        productPrice.setText(String.format(Locale.ENGLISH,"$%.2f",productsList.get(position).getPrice()));
        return v;
    }
}
