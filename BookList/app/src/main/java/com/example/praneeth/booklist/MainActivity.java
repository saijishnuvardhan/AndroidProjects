package com.example.praneeth.booklist;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.Context;
import android.app.LoaderManager.LoaderCallbacks;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {
    String bname;
    public String REQUEST_URL= "";
    RecyclerView.Adapter bAdapter;
    public static final int GBook=1;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText editText;
    ProgressBar progressBar;
    Button button;
    ArrayList<Required> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progress);
       editText=(EditText)findViewById(R.id.etext);
       bname=editText.getText().toString();
       recyclerView=(RecyclerView)findViewById(R.id.list);
       layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(layoutManager);

       button=(Button)findViewById(R.id.Button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressBar.setVisibility(View.VISIBLE);
               arrayList.clear();
               search();
           }
       });

       }


    public void search(){
        bname=editText.getText().toString();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);

        Call<result> call=api.getresult(bname,20);


        call.enqueue(new Callback<result>() {

            @Override
            public void onResponse(Call<result> call, Response<result> response) {
                result result=response.body();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<result.getItems().size();i++) {
                    try {
                        progressBar.setVisibility(View.INVISIBLE);
                        String name = result.getItems().get(i).getVolumeInfo().getTitle();
                        String author = result.getItems().get(i).getVolumeInfo().getAuthors().get(0);
                        String url = result.getItems().get(i).getVolumeInfo().getInfoLink();
                        String thumbnail = result.getItems().get(i).getVolumeInfo().getImageLinks().getThumbnail();

                        arrayList.add(new Required(name, author, url, thumbnail));
                    }catch (NullPointerException n){
                        n.getMessage();
                    }
                }

                bAdapter=new Book_Adapter(getApplicationContext(),arrayList);
                recyclerView.setAdapter(bAdapter);
            }

            @Override
            public void onFailure(Call<result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG ).show();
            }
        });

    }




}
