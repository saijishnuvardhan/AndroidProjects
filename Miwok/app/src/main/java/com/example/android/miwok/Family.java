package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        ArrayList<Work> numbers=new ArrayList<Work>();
        numbers.add(new Work("Daddy","Nanna"));
        numbers.add(new Work("Mummy","Amma"));
        numbers.add(new Work("Maternal Grand Mother","Ammama"));
        numbers.add(new Work("Grand Father","Tatayya"));
        numbers.add(new Work("Paternal Grand Mother","nannama"));
        numbers.add(new Work("Sister","chelli/akka"));
        numbers.add(new Work("Brother","anna/tammudu"));
        numbers.add(new Work("Nephew","menalludu"));
        numbers.add(new Work("Niece","menakodalu"));
        numbers.add(new Work("Great Grand Father","muthata"));
        WorkAdapter item=new WorkAdapter(this,numbers);
        ListView listView=(ListView) findViewById(R.id.family);
        listView.setAdapter(item);
    }
}
