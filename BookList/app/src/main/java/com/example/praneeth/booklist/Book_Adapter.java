package com.example.praneeth.booklist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Book_Adapter extends RecyclerView.Adapter<BookVHolder> {

    private Context context;
    private List<Book> books;
    public Book_Adapter(Context context, List<Book> books){
        this.context=context;
        this.books=books;
    }

    @NonNull
    @Override
    public BookVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.activity_book, parent,false);
        BookVHolder bookVHolder=new BookVHolder(v);
        return bookVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookVHolder holder, final int position) {

        holder.textView.setText(books.get(position).gettitle());
        holder.text.setText(books.get(position).getAuthor());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=books.get(position).getUrl();
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(context, intent, null);
            }
        });
        Glide.with(context).load(Uri.parse(books.get(position).getThumbnail())).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
