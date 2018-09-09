package com.example.praneeth.shoping_cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SalesActivity extends AppCompatActivity {
    View loader;
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager1;
    Sales_Adapter sadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        loader=findViewById(R.id.progress);
        loader.setVisibility(View.VISIBLE);
         recycler=findViewById(R.id.re_buy);
        Retrofit retrofit = ApiClient.retrofitCall();
        Api api = retrofit.create(Api.class);

        Call<List<Buy>> c;
        c=api.getSalesInfo();

        c.enqueue(new Callback<List<Buy>>() {
            @Override
            public void onResponse(Call<List<Buy>> call, Response<List<Buy>> response) {
                List<Buy> array=response.body();
                layoutManager1=new LinearLayoutManager(getApplicationContext());
                recycler.setLayoutManager(layoutManager1);
                sadapter=new Sales_Adapter(getApplicationContext(),array);
                loader.setVisibility(View.INVISIBLE);
                recycler.setAdapter(sadapter);

            }

            @Override
            public void onFailure(Call<List<Buy>> call, Throwable t) {
                Log.i("url",call.request().url().toString());
                Toast.makeText(getApplicationContext(), call.request().url().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
