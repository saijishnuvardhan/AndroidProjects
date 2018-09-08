package com.example.praneeth.shoping_cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter sadapter;
    List<Buy> buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

         recycler=findViewById(R.id.recycler1);
         layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
         recycler.setLayoutManager(layoutManager);
         buy=new ArrayList<Buy>();

         Api api=ApiClient.retrofitCall().create(Api.class);

        Call<List<Buy>> c=api.getSalesInfo();

        c.enqueue(new Callback<List<Buy>>() {
            @Override
            public void onResponse(Call<List<Buy>> call, Response<List<Buy>> response) {
                buy=response.body();
                renderPhones();
            }

            @Override
            public void onFailure(Call<List<Buy>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
    public void renderPhones(){
        sadapter=new Sales_Adapter(this,buy);
        sadapter.notifyDataSetChanged();
        recycler.setAdapter(sadapter);

    }
}
