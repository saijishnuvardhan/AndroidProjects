package com.example.praneeth.session_1;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

class FruitAdaptar extends RecyclerView.Adapter<FruitAdaptar.FruitViewHolder>  {

    private ArrayList<Fruit> fNameList;


    public FruitAdaptar( ArrayList<Fruit> createfruits) {
        fNameList=createfruits;
    }

    @NonNull
    @Override
    public FruitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FruitViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FruitViewHolder fruitViewHolder, int i) {
      fruitViewHolder.fruitImage.setImageResource(fNameList.get(i).getImageid());
      fruitViewHolder.fruitText.setText(fNameList.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return fNameList.size();
    }

    class FruitViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_fruit_image)
        ImageView fruitImage;

        @BindView(R.id.tv_fruit_name)
        TextView fruitText;

        public FruitViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            }
    }


}
