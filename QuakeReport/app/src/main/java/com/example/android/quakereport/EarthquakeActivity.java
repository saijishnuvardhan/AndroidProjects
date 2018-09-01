/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Quake>> {

    private static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String SAMPLE_JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    public static final int EARTHQUAKE_ID=1;
    TextView textView;
    ProgressBar pb;
    protected Quake_adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        textView=(TextView)findViewById(R.id.text);
        earthquakeListView.setEmptyView(textView);
        mAdapter = new Quake_adapter(EarthquakeActivity.this,0, new ArrayList<Quake>());
        earthquakeListView.setAdapter(mAdapter);
        pb=(ProgressBar)findViewById(R.id.progress);
        getLoaderManager().initLoader(EARTHQUAKE_ID, null, this);



    }

    @Override
    public void onLoaderReset(Loader<List<Quake>> loader) {
       mAdapter.clear();

    }

    @Override
    public void onLoadFinished(Loader<List<Quake>> loader, List<Quake> data) {
        pb.setVisibility(View.GONE);
        textView.setText(R.string.no_earthquakes);
      mAdapter.clear();

      if(data!=null&&!data.isEmpty()){
         mAdapter.addAll(data);
      }
    }

    @Override
    public Loader<List<Quake>> onCreateLoader(int id, Bundle args) {

        return new EarthquakeLoader(this,SAMPLE_JSON_RESPONSE);
            }
}
