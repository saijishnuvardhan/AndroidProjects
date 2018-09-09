package com.example.praneeth.shoping_cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Sales_Adapter extends RecyclerView.Adapter<SalesViewHolder> {

    Context context;
    List<Buy> info;

    public  Sales_Adapter(Context context, List<Buy> info){
        this.context=context;
        this.info=info;
    }
    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.buyinfo,parent,false );
        SalesViewHolder svh=new SalesViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {
        holder.textView.setText("Invoice Number:"+info.get(position).getInovice());
        holder.textView1.setText("Model:"+info.get(position).getModel());
        holder.textView2.setText("Quantity:"+info.get(position).getQuantity());
        holder.textView3.setText("Username:"+info.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }
}
