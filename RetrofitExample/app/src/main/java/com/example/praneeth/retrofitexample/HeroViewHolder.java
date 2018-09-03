package com.example.praneeth.retrofitexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeroViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private LinearLayout linearLayout;
    private ImageView imageView;
    public HeroViewHolder(View itemView) {
        super(itemView);
        textView=(TextView)itemView.findViewById(R.id.textview);
         linearLayout=(LinearLayout)itemView.findViewById(R.id.linear);
         imageView=(ImageView)itemView.findViewById(R.id.img);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
