package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkAdapter extends ArrayAdapter<Work>{
    private int color;
    public WorkAdapter(Activity context, ArrayList<Work> androidFlavors,int color) {

        super(context, 0, androidFlavors);
        this.color=color;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View list=convertView;
       if(list==null){
           list= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
       }

       Work currentWork=getItem(position);
        TextView mtal=(TextView)list.findViewById(R.id.miwok);
        mtal.setText(currentWork.getMiwokTranslation());

        TextView dtal=(TextView)list.findViewById(R.id.english);
        dtal.setText(currentWork.getdTranslation());

        ImageView image=(ImageView)list.findViewById(R.id.im);
        if(currentWork.hasImage()){
            image.setImageResource(currentWork.getResourceID());
            image.setVisibility(View.VISIBLE);
        }
        else{
            image.setVisibility(View.GONE);
        }

        View textContainer=list.findViewById(R.id.textc);
        int c= ContextCompat.getColor(getContext(), color);
        textContainer.setBackgroundColor(c);
       return list;
    }
}
