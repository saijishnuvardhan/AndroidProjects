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
        numbers.add(new Work("father","Nanna",R.drawable.family_father));
        numbers.add(new Work("Mummy","Amma",R.drawable.family_mother));
        numbers.add(new Work("Maternal Grand Mother","Ammama",R.drawable.family_grandmother));
        numbers.add(new Work("Grand Father","Tatayya",R.drawable.family_grandfather));
        numbers.add(new Work("Paternal Grand Mother","nannama",R.drawable.family_grandmother));
        numbers.add(new Work("Older Sister","akka",R.drawable.family_older_sister));
        numbers.add(new Work("Older Brother","annayya",R.drawable.family_older_brother));
        numbers.add(new Work("Younger Sister","Chelli",R.drawable.family_younger_sister));
        numbers.add(new Work("Younger Brother","tammudu",R.drawable.family_younger_brother));
        numbers.add(new Work("Daughter","kuthuru",R.drawable.family_daughter));
        numbers.add(new Work("Son","koduku",R.drawable.family_son));
        WorkAdapter item=new WorkAdapter(this,numbers,R.color.category_family);
        ListView listView=(ListView) findViewById(R.id.family);
        listView.setAdapter(item);
    }
}
