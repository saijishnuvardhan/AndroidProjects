package com.example.praneeth.booklist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Book_Adapter extends ArrayAdapter<Book> {
    public Book_Adapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View BookView=convertView;
        if(BookView==null){
            BookView= LayoutInflater.from(getContext()).inflate(R.layout.activity_book,parent ,false);
        }
        final Book currentpos=getItem(position);
        TextView title=(TextView)BookView.findViewById(R.id.title);
        title.setText(currentpos.gettitle());

        TextView author=(TextView)BookView.findViewById(R.id.author);
        author.setText(currentpos.getAuthor());

        LinearLayout l= BookView.findViewById(R.id.linear);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=currentpos.getUrl();
                Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(getContext(),in,null);
            }
        });

        return BookView;
    }
}
