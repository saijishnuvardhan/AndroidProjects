package com.example.praneeth.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView list;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(RecyclerView) findViewById(R.id.list);
        layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        list.setLayoutManager(layoutManager);

        getHeros();
    }

    public void getHeros(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);

        Call<List<Heros>> c=api.getHeros();

        c.enqueue(new Callback<List<Heros>>() {
            @Override
            public void onResponse(Call<List<Heros>> call, Response<List<Heros>> response) {
                List<Heros> listv=response.body();

                List<Heros> heros=new ArrayList<>();

                for(int i=0;i<listv.size();i++){
                   String name=(listv.get(i).getName());
                   String url=(listv.get(i).getImageurl());
                   heros.add( new Heros(name,url));
                }
                list.setAdapter(new HeroAdapter(getApplicationContext(),heros));
            }

            @Override
            public void onFailure(Call<List<Heros>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
