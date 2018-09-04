package com.example.praneeth.booklist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookVHolder extends RecyclerView.ViewHolder {
    LinearLayout layout;
    TextView textView;
    TextView text;
    ImageView imageView;
    public BookVHolder(View itemView) {
        super(itemView);
        layout=(LinearLayout)itemView.findViewById(R.id.linear);
        textView=(TextView)itemView.findViewById(R.id.title);
        text=(TextView)itemView.findViewById(R.id.author);
        imageView=(ImageView)itemView.findViewById(R.id.th);
    }
}
