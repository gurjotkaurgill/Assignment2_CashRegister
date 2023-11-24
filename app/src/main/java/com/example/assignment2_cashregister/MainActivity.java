package com.example.assignment2_cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    ArrayList<Product> productsList;
    ArrayList<PurchaseHistory> purchaseHistories;
    ProductBaseAdapter productBaseAdapter;
    TextView productTypeTextView,productQuantityTextView,productTotalTextView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button managerBtn, clearBtn, buyButton;
    int indexOfProductSelected = -1;
    boolean isProductSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        productsList = ((AppLevelVars)getApplicationContext()).getProducts();
        purchaseHistories = ((AppLevelVars) getApplicationContext()).getPurchaseHistories();
        productBaseAdapter= new ProductBaseAdapter(productsList,MainActivity.this);
        listView.setAdapter(productBaseAdapter);

        initializeViews();
        setClickListeners();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //clearQuantityAndPriceOnScreen();
                productTypeTextView.setText(productsList.get(position).getName());
                isProductSelected = true;
                indexOfProductSelected = position;
                if(!productQuantityTextView.getText().toString().isEmpty())
                    updatePriceText();
            }
        });

        if(savedInstanceState != null) {
            productTypeTextView.setText(savedInstanceState.getString("selectedProduct"));
            productQuantityTextView.setText(savedInstanceState.getString("selectedQuantity"));
            productTotalTextView.setText(savedInstanceState.getString("calculatedPrice"));
            isProductSelected = savedInstanceState.getBoolean("isProductSelected");
            indexOfProductSelected = savedInstanceState.getInt("indexOfProductSelected");
        }
    }

    protected void initializeViews(){
        productTypeTextView = findViewById(R.id.productTypeTextView);
        productQuantityTextView = findViewById(R.id.productQuantityTextView);
        productTotalTextView = findViewById(R.id.productTotalTextView);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8= findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        managerBtn = findViewById(R.id.managerButton);
        clearBtn = findViewById(R.id.btnClear);
        buyButton = findViewById(R.id.btnBuy);
    }
    void setClickListeners(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        managerBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        buyButton.setOnClickListener(this);
    }

    void clearQuantityAndPriceOnScreen() {
        productQuantityTextView.setText("");
        productTotalTextView.setText("");
    }
    void clearEverything() {
        clearQuantityAndPriceOnScreen();
        productTypeTextView.setText("");
        isProductSelected=false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnClear) {
            clearQuantityAndPriceOnScreen();
        }
        else if(v.getId() == R.id.btnBuy){
            if(!isProductSelected || productQuantityTextView.getText().toString().isEmpty()){
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_LONG).show();
            }
            else {
                int quantityBought = Integer.parseInt(productQuantityTextView.getText().toString());
                double priceAtWhichBought = Double.parseDouble(productTotalTextView.getText().toString());
                String productBoughtName = productsList.get(indexOfProductSelected).getName();
                productsList.get(indexOfProductSelected).setQuantity(
                        productsList.get(indexOfProductSelected).getQuantity()
                                -quantityBought);
                String currentTime = Calendar.getInstance().getTime().toString();
                purchaseHistories.add(new PurchaseHistory(productBoughtName,quantityBought,priceAtWhichBought, currentTime));
                //purchaseHistories.add(new PurchaseHistory(productBoughtName,quantityBought,priceAtWhichBought, LocalDateTime.now().toString()));
                productBaseAdapter.notifyDataSetChanged();
                clearEverything();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String builderMessage = String.format(Locale.ENGLISH,"Your purchase is %d %s for $%.2f",quantityBought,productBoughtName,priceAtWhichBought);
                builder.setMessage(builderMessage);
                builder.setTitle("Thank you for your purchase");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
        else if(v.getId() == R.id.managerButton){
            Intent managerMenuIntent = new Intent(this,ManagerMenuActivity.class);
            startActivity(managerMenuIntent);
        }
        else {
                //number buttons pressed
                String pressedBtnText = ((Button) v).getText().toString();
                productQuantityTextView.setText(String.format("%s%s", productQuantityTextView.getText(), pressedBtnText));
                if(isProductSelected) {
                    updatePriceText();
                }
        }
    }
    public void updatePriceText(){
        double priceOfSelectedProduct = productsList.get(indexOfProductSelected).getPrice();
        int maxQtyOfProduct = productsList.get(indexOfProductSelected).getQuantity();
        int quantity = Integer.parseInt(String.valueOf(productQuantityTextView.getText()));
        String totalPrice = String.format(Locale.ENGLISH,"%.2f",(quantity * priceOfSelectedProduct));
        productTotalTextView.setText(totalPrice);
        //productTotalTextView.setText(String.valueOf(totalPrice));
        if (quantity > maxQtyOfProduct) {
            Toast.makeText(this, "Not enough quantity in stock!", Toast.LENGTH_LONG).show();
            clearQuantityAndPriceOnScreen();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("selectedProduct",productTypeTextView.getText().toString());
        outState.putString("selectedQuantity",productQuantityTextView.getText().toString());
        outState.putString("calculatedPrice",productTotalTextView.getText().toString());
        outState.putBoolean("isProductSelected",isProductSelected);
        outState.putInt("indexOfProductSelected",indexOfProductSelected);
    }
}