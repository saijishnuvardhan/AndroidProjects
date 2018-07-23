package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Colours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colours);
        ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("Red","yerupu",R.drawable.color_red));
        numbers.add(new Work("Yellow","pasupu",R.drawable.color_mustard_yellow));
        numbers.add(new Work("Dusty yellow","gandham",R.drawable.color_dusty_yellow));
        numbers.add(new Work("Green","Aakupaccha",R.drawable.color_green));
        numbers.add(new Work("Black","Nalupu",R.drawable.color_black));
        numbers.add(new Work("White","telupu",R.drawable.color_white));
        numbers.add(new Work("Gray","Budidha",R.drawable.color_gray));
        numbers.add(new Work("Brown","Braun raṅgu",R.drawable.color_brown));
        WorkAdapter item=new WorkAdapter(this,numbers);
        ListView listView=(ListView) findViewById(R.id.colors);
        listView.setAdapter(item);
    }
}
