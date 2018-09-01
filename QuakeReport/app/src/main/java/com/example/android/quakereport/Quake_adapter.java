package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class Quake_adapter extends ArrayAdapter<Quake> {
    public Quake_adapter(@NonNull Context context, int resource, @NonNull List<Quake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View Quakeitem=convertView;
        if(Quakeitem==null){
            Quakeitem= LayoutInflater.from(getContext()).inflate(R.layout.activity_quake,parent,false);
        }
        final Quake currentpos=getItem(position);

        TextView mag=(TextView)Quakeitem.findViewById(R.id.magnitude);
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        String magnitude=decimalFormat.format(currentpos.getMagnitude());
        mag.setText(magnitude);
        GradientDrawable magnitudecircle=(GradientDrawable)mag.getBackground();
        int magnitudecolor=getMagnitudeColor(currentpos.getMagnitude());
        magnitudecircle.setColor(magnitudecolor);

        TextView direction=(TextView)Quakeitem.findViewById(R.id.direction);
        direction.setText(currentpos.getDirection());

        TextView city=(TextView)Quakeitem.findViewById(R.id.city);
        city.setText(currentpos.getCity());

        TextView date=(TextView)Quakeitem.findViewById(R.id.date);
        date.setText(currentpos.getDate());

        LinearLayout l=(LinearLayout)Quakeitem.findViewById(R.id.quake);

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=currentpos.getUrl();
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(getContext(),intent,null);
            }

        });

        TextView time=(TextView)Quakeitem.findViewById(R.id.time);
        time.setText(currentpos.getTime());


        return Quakeitem;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudecolorid;
        int magnitudefloor=(int)Math.floor(magnitude);
        switch(magnitudefloor){
            case 0:
            case 1:
                magnitudecolorid=R.color.magnitude1;
                break;
            case 2:
                magnitudecolorid=R.color.magnitude2;
                break;
            case 3:
                magnitudecolorid=R.color.magnitude3;
                break;
            case 4:
                magnitudecolorid=R.color.magnitude4;
                break;
            case 5:
                magnitudecolorid=R.color.magnitude5;
                break;
            case 6:
                magnitudecolorid=R.color.magnitude6;
                break;
            case 7:
                magnitudecolorid=R.color.magnitude7;
                break;
            case 8:
                magnitudecolorid=R.color.magnitude8;
                break;
            case 9:
                magnitudecolorid=R.color.magnitude9;
                break;
            default:
                magnitudecolorid=R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),magnitudecolorid);
    }
}
