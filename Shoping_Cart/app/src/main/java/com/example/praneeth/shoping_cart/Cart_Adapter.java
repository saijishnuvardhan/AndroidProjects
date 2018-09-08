package com.example.praneeth.shoping_cart;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<CartViewHolder> {
    Context context;
    List<Phone> phones;
    public Cart_Adapter(Context context, List<Phone> list){
        this.context=context;
        phones=list;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.phone,parent,false );
        CartViewHolder cvh=new CartViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.model.setText(phones.get(position).getModel());
        holder.manufacturer.setText(phones.get(position).getManufacturer());
        holder.price.setText("Rs "+phones.get(position).getPrice());

        Glide.with(context).load(Uri.parse(phones.get(position).getImage())).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return phones.size();
    }
}
