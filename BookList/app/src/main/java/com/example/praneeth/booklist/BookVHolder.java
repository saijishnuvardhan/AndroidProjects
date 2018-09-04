package com.example.praneeth.booklist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookVHolder extends RecyclerView.ViewHolder {
    CardView layout;
    TextView textView;
    TextView text;
    ImageView imageView;
    public BookVHolder(View itemView) {
        super(itemView);
        layout=(CardView) itemView.findViewById(R.id.linear);
        textView=(TextView)itemView.findViewById(R.id.title);
        text=(TextView)itemView.findViewById(R.id.author);
        imageView=(ImageView)itemView.findViewById(R.id.th);
    }
}
