package com.example.assignment2_cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements View.OnClickListener{

    TextView productSelectedTextView;
    EditText quantityUpdaterEditText;
    Button okBtn, cancelBtn;
    ListView listView;
    ProductBaseAdapter productBaseAdapter;
    ArrayList<Product> productsList;
    boolean isProductSelected = false;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restock_layout);
        productSelectedTextView = findViewById(R.id.restock_productChosenTextView);
        quantityUpdaterEditText = findViewById(R.id.restock_newQtyEditText);
        okBtn = findViewById(R.id.okBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        listView = findViewById(R.id.restock_listView);

        productsList = ((AppLevelVars)getApplicationContext()).getProducts();
        productBaseAdapter= new ProductBaseAdapter(productsList,RestockActivity.this);
        listView.setAdapter(productBaseAdapter);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productSelectedTextView.setText(productsList.get(position).getName());
                isProductSelected = true;
                index = position;
            }
        });

        if(savedInstanceState != null){
            isProductSelected = savedInstanceState.getBoolean("isProductSelected");
            productSelectedTextView.setText(savedInstanceState.getString("productSelected"));
            quantityUpdaterEditText.setText(savedInstanceState.getString("quantityChosen"));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.okBtn){
            if(!isProductSelected || quantityUpdaterEditText.getText().toString().isEmpty())
                Toast.makeText(this, "All fields are required", Toast.LENGTH_LONG).show();
            else {
                //update quantity
                int newQuantity = Integer.parseInt(quantityUpdaterEditText.getText().toString());
                productsList.get(index).setQuantity(productsList.get(index).getQuantity()+newQuantity);
                productBaseAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Quantity added", Toast.LENGTH_SHORT).show();
                isProductSelected=false;
                quantityUpdaterEditText.setText("");
                productSelectedTextView.setText("");
            }
        }
        else {
            //cancel button pressed
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isProductSelected",isProductSelected);
        outState.putString("productSelected",productSelectedTextView.getText().toString());
        outState.putString("quantityChosen",quantityUpdaterEditText.getText().toString());
    }
}