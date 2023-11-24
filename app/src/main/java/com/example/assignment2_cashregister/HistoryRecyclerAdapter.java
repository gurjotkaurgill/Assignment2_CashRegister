package com.example.assignment2_cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder> {
    ArrayList<PurchaseHistory> purchaseHistories;
    Context context;
    PurchaseHistoryClickListener listener;

    public HistoryRecyclerAdapter(ArrayList<PurchaseHistory> purchaseHistories, Context context) {
        this.purchaseHistories = purchaseHistories;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products_row,parent,false);
        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        TextView leftTextView = holder.itemView.findViewById(R.id.leftTextView_Row);
        TextView rightTextView = holder.itemView.findViewById(R.id.rightTextView_Row);
        TextView bottomTextView = holder.itemView.findViewById(R.id.bottomTextView_Row);
        leftTextView.setText(purchaseHistories.get(position).getPurchaseProductName());
        String price = "$" + purchaseHistories.get(position).getPurchasePrice();
        rightTextView.setText(price);
        bottomTextView.setText(String.valueOf(purchaseHistories.get(position).getPurchaseQuantity()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onHistoryItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchaseHistories.size();
    }

    interface PurchaseHistoryClickListener {
        void onHistoryItemClicked(int position);
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
