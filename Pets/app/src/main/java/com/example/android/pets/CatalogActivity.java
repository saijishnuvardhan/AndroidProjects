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
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pets.data.PetDBHelper;
import com.example.android.pets.data.PetsContract.PetsEntry;


/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    private PetDBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new PetDBHelper(this);
        displayDatabaseInfo();
    }

    private void insertData(){
        ContentValues cd = new ContentValues();
        cd.put(PetsEntry.COLUMN_PET_NAME,"Toto");
        cd.put(PetsEntry.COLUMN_PET_BREED,"Terrier");
        cd.put(PetsEntry.COLUMN_PET_GENDER,PetsEntry.GENDER_MALE);
        cd.put(PetsEntry.COLUMN_PET_WEIGHT,7);

        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        long newRowId= db.insert(PetsEntry.TABLE_NAME,null,cd);
        Log.i("Catalog Activity","New Row Id"+newRowId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();


    }
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection={
                PetsEntry.COLUMN_ID,
                PetsEntry.COLUMN_PET_NAME,
                PetsEntry.COLUMN_PET_BREED,
                PetsEntry.COLUMN_PET_GENDER,
                PetsEntry.COLUMN_PET_WEIGHT
        };

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.query(PetsEntry.TABLE_NAME,projection,null,null,null,null,null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PetsEntry.COLUMN_ID + " - " +
                    PetsEntry.COLUMN_PET_NAME +" - "+PetsEntry.COLUMN_PET_BREED+" - "
                    +PetsEntry.COLUMN_PET_GENDER+" -"+PetsEntry.COLUMN_PET_WEIGHT+ "\n");

            int idColumnIndex = cursor.getColumnIndex(PetsEntry.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(PetsEntry.COLUMN_PET_NAME);
            int breedcolumnIndex = cursor.getColumnIndex(PetsEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PetsEntry.COLUMN_PET_GENDER);
            int weightColoumnIndex= cursor.getColumnIndex(PetsEntry.COLUMN_PET_WEIGHT);

            while (cursor.moveToNext()){
                int id=cursor.getInt(idColumnIndex);
                String name=cursor.getString(nameColumnIndex);
                String breed = cursor.getString(breedcolumnIndex);
                int gender=cursor.getInt(genderColumnIndex);
                int weight = cursor.getInt(weightColoumnIndex);

                displayView.append(("\n" + id + " - " +
                        name+ " - "+breed + " - "+ gender +" - "+weight));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertData();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
