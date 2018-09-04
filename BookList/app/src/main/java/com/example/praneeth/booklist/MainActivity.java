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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {
    String bname;
    public String REQUEST_URL= "";
    RecyclerView.Adapter bAdapter;
    public static final int GBook=1;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editText=(EditText)findViewById(R.id.etext);
        bname=editText.getText().toString();
       recyclerView=(RecyclerView)findViewById(R.id.list);
       layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(layoutManager);

     bAdapter=new Book_Adapter(this, new ArrayList<Book>());

       Button b=(Button)findViewById(R.id.Button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            search(view);
            }
        });


    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        if(data!=null&&!data.isEmpty()){


           bAdapter= new Book_Adapter(this,data);
            recyclerView.setAdapter(bAdapter);
        }

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this,REQUEST_URL);
    }

    public void search(View view){
        if (bname!=null) {
            bname=editText.getText().toString();
            REQUEST_URL="https://www.googleapis.com/books/v1/volumes?q="+bname+"&maxResult=20";
            getLoaderManager().initLoader(GBook, null, this);
        }

    }




}
