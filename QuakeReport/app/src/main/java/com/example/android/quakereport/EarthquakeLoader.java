package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class EarthquakeLoader extends AsyncTaskLoader<List<Quake>>{

    public static final String LOG_TAG=EarthquakeLoader.class.getSimpleName();
    String murl;
    List<Quake> list;

    public EarthquakeLoader(Context context,String url){
        super(context);
        murl=url;
    }


    @Override
    protected void onStartLoading() {
       forceLoad();
    }

    @Override
    public List<Quake> loadInBackground() {
        if(murl==null){
            return null;
        }
        list=QueryUtils.fetchEarthquakeData(murl);
        return list;
    }


}
