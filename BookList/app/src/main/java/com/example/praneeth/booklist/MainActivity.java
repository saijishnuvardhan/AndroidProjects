package com.example.praneeth.booklist;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.Context;
import android.app.LoaderManager.LoaderCallbacks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {
    String bname;
    public String REQUEST_URL= "";
    Book_Adapter bAdapter;
    public static final int GBook=1;
    EditText editText;
     ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editText=(EditText)findViewById(R.id.etext);
        bname=editText.getText().toString();
       ls=(ListView)findViewById(R.id.list);
        bAdapter=new Book_Adapter(this,0,new ArrayList<Book>());
        ls.setAdapter(bAdapter);

        Button b=(Button)findViewById(R.id.Button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bAdapter.clear();
            search(view);
            }
        });


    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bAdapter.clear();
        bAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        bAdapter.clear();
        if(data!=null&&!data.isEmpty()){
            bAdapter.addAll(data);
        }

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this,REQUEST_URL);
    }

    public void search(View view){
        if (bname!=null) {
            bname=editText.getText().toString();
            bAdapter.clear();
            REQUEST_URL="https://www.googleapis.com/books/v1/volumes?q="+bname+"&maxResult=20";
            getLoaderManager().initLoader(GBook, null, this);
        }

    }




}
