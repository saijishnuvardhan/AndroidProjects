package com.example.praneeth.retrofitexample;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroViewHolder> {
    private Context mContext;
    private List<Heros> heros;
    public HeroAdapter(Context context, List<Heros> heros) {
        mContext=context;
        this.heros=heros;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.herol, parent,false);
        HeroViewHolder hvh=new HeroViewHolder(v);
        return hvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {

        Glide.with(mContext).load(Uri.parse(heros.get(position).getImageurl())).into(holder.getImageView());
        holder.getTextView().setText(heros.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return heros.size();
    }

}
