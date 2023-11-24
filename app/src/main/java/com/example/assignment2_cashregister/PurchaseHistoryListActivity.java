package com.example.assignment2_cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class PurchaseHistoryListActivity extends AppCompatActivity implements HistoryRecyclerAdapter.PurchaseHistoryClickListener {
    RecyclerView recyclerView;
    ArrayList<PurchaseHistory> purchaseHistories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_history_list);
        recyclerView = findViewById(R.id.historyRecyclerView);
        purchaseHistories = ((AppLevelVars)getApplication()).getPurchaseHistories();

        HistoryRecyclerAdapter historyRecyclerAdapter = new HistoryRecyclerAdapter(purchaseHistories,this);
        historyRecyclerAdapter.listener = this;
        recyclerView.setAdapter(historyRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onHistoryItemClicked(int position) {
        Intent historyDetailIntent = new Intent(this, HistoryDetailActivity.class);
        historyDetailIntent.putExtra("selected history item",purchaseHistories.get(position));
        startActivity(historyDetailIntent);
        //Toast.makeText(this,purchaseHistories.get(position).getPurchaseProductName(),Toast.LENGTH_LONG).show();
    }
}