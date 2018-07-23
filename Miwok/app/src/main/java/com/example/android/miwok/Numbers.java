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
        numbers.add(new Work("One","lutti",R.drawable.number_one));
        numbers.add(new Work("Two","ottiko",R.drawable.number_two));
        numbers.add(new Work("Three","tolookosu",R.drawable.number_three));
        numbers.add(new Work("Four","oyyisa",R.drawable.number_four));
        numbers.add(new Work("Five","massokka",R.drawable.number_five));
        numbers.add(new Work("Six","temmokka",R.drawable.number_six));
        numbers.add(new Work("Seven","kenekaku",R.drawable.number_seven));
        numbers.add(new Work("Eight","kawinta",R.drawable.number_eight));
        numbers.add(new Work("Nine","wo'e",R.drawable.number_nine));
        numbers.add(new Work("Ten","na'aacha",R.drawable.number_ten));
        WorkAdapter item=new WorkAdapter(this,numbers);
        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(item);
        }

    }

