package com.example.praneeth.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

                                                                                                                                                                                                                                                                                                                                                                                public class BookLoader extends AsyncTaskLoader<List<Book>> {
    public static final String LOG_TAG=BookLoader.class.getSimpleName();
    List<Book> list;
    String murl;
    public BookLoader(Context context,String s) {
        super(context);
        murl=s;
    }

    @Override
    protected void onStartLoading() {
        if(murl!=null){
        forceLoad();}
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        if(murl==null){
            return null;
        }
        list=QueryUtils.fetchdata(murl);
        return list;
    }
}
