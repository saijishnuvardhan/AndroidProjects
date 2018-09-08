package com.example.praneeth.shoping_cart;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CartViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView model;
    TextView manufacturer;
    TextView price;
    LinearLayout layout;
    public CartViewHolder(View itemView) {
        super(itemView);
         imageView=itemView.findViewById(R.id.image);
        model=itemView.findViewById(R.id.model);
         manufacturer=itemView.findViewById(R.id.manufacturer);
         price=itemView.findViewById(R.id.price);
         layout=itemView.findViewById(R.id.card);
    }
}
