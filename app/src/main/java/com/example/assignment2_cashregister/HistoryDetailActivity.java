package com.example.assignment2_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView historyDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail);
        historyDetailTextView = findViewById(R.id.historyDetailTextView);
        PurchaseHistory historyObject =  getIntent().getParcelableExtra("selected history item");
        if (historyObject != null) {
            historyDetailTextView.setText(historyObject.toString());
        }
    }
}