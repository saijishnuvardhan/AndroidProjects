package com.example.praneeth.shoping_cart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SalesViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    public SalesViewHolder(View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.inovice);
        textView1=itemView.findViewById(R.id.Model);
        textView2=itemView.findViewById(R.id.quantity);
        textView3=itemView.findViewById(R.id.username);
    }
}
