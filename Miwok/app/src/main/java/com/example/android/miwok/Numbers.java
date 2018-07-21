package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Numbers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("One","lutti"));
        numbers.add(new Work("Two","ottiko"));
        numbers.add(new Work("Three","tolookosu"));
        numbers.add(new Work("Four","oyyisa"));
        numbers.add(new Work("Five","massokka"));
        numbers.add(new Work("Six","temmokka"));
        numbers.add(new Work("Seven","kenekaku"));
        numbers.add(new Work("Eight","kawinta"));
        numbers.add(new Work("Nine","wo'e"));
        numbers.add(new Work("Ten","na'aacha"));
        WorkAdapter item=new WorkAdapter(this,numbers);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(item);
        }

    }

