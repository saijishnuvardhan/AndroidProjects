package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetsContract.PetsEntry;

public class PetDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "pets.db";


    public PetDBHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String  SQL_CREATE_PETS_TABLE="CREATE TABLE "+PetsEntry.TABLE_NAME+"("+PetsEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +PetsEntry.COLUMN_PET_NAME+" TEXT NOT NULL, "
                +PetsEntry.COLUMN_PET_BREED+" TEXT, "
                +PetsEntry.COLUMN_PET_GENDER+" INTEGER NOT NULL,"
                +PetsEntry.COLUMN_PET_WEIGHT+" INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
