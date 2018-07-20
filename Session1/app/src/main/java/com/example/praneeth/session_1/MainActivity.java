package com.example.praneeth.session_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fruit> fname;
    private RecyclerView.Adapter fruitAdaptar;

    @BindView(R.id.rv_fruit_list_view)
    RecyclerView fRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fruitAdaptar =new FruitAdaptar(createfruits());
        fRecycleView.setAdapter(fruitAdaptar);

    }

    public ArrayList<Fruit> createfruits(){
        fname=new ArrayList<>();
        fname.add(new Fruit("Apple",R.drawable.apple1));
        fname.add(new Fruit("Banana",R.drawable.banana));
        fname.add(new Fruit("berry",R.drawable.cherries));
        fname.add(new Fruit("chives",R.drawable.chives));
        fname.add(new Fruit("grapes",R.drawable.grapes));
        fname.add(new Fruit("orange",R.drawable.orange));
        fname.add(new Fruit("pear",R.drawable.pear));
        fname.add(new Fruit("pineapple",R.drawable.pineapple));
        fname.add(new Fruit("pomogranate",R.drawable.pomegranate));
        fname.add(new Fruit("raseberry",R.drawable.raspberry));
        fname.add(new Fruit("strawberry",R.drawable.strawberry));
        fname.add(new Fruit("tomato",R.drawable.tomato));
        fname.add(new Fruit("watermelon",R.drawable.watermelon));
        Log.v("Fruit List",fname.toString());
        return  fname;
    }


}
