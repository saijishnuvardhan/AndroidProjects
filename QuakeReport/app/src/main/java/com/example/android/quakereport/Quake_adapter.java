package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class Quake_adapter extends ArrayAdapter<Quake> {
    public Quake_adapter(@NonNull Context context, @NonNull List<Quake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View Quakeitem=convertView;
        if(Quakeitem==null){
            Quakeitem= LayoutInflater.from(getContext()).inflate(R.layout.activity_quake,parent,false);
        }
        Quake currentpos=getItem(position);

        TextView mag=(TextView)Quakeitem.findViewById(R.id.magnitude);
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        String magnitude=decimalFormat.format(currentpos.getMagnitude());
        mag.setText(magnitude);

        TextView direction=(TextView)Quakeitem.findViewById(R.id.direction);
        direction.setText(currentpos.getDirection());

        TextView city=(TextView)Quakeitem.findViewById(R.id.city);
        city.setText(currentpos.getCity());

        TextView date=(TextView)Quakeitem.findViewById(R.id.date);
        date.setText(currentpos.getDate());

        TextView time=(TextView)Quakeitem.findViewById(R.id.time);
        time.setText(currentpos.getTime());

        return Quakeitem;
    }
}
