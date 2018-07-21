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
        numbers.add(new Work("Red","yerupu"));
        numbers.add(new Work("Blue","neelam"));
        numbers.add(new Work("Yellow","pasupu"));
        numbers.add(new Work("Orange","gandham"));
        numbers.add(new Work("Green","Aakupaccha"));
        numbers.add(new Work("Black","Nalupu"));
        numbers.add(new Work("White","telupu"));
        numbers.add(new Work("Pink","gulabhi"));
        numbers.add(new Work("Grey","Budidha"));
        numbers.add(new Work("Gold","Bangaram"));
        WorkAdapter item=new WorkAdapter(this,numbers);
        ListView listView=(ListView) findViewById(R.id.colors);
        listView.setAdapter(item);
    }
}
