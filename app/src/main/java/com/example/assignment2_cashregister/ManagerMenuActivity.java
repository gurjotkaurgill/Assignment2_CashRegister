package com.example.assignment2_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerMenuActivity extends AppCompatActivity implements View.OnClickListener{

    Button historyBtn,restockBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_menu);
        historyBtn = findViewById(R.id.historyBtn);
        restockBtn = findViewById(R.id.restockBtn);
        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.historyBtn){
            Intent historyIntent = new Intent(this,PurchaseHistoryListActivity.class);
            startActivity(historyIntent);
        }
        else {
            Intent restockIntent = new Intent(this,RestockActivity.class);
            startActivity(restockIntent);
        }
    }
}