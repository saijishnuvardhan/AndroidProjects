package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.android.pets.data.PetsContract.PetsEntry;

public class PetProvider extends ContentProvider {

    public static final String LOG_TAG=PetProvider.class.getSimpleName();

    public static final UriMatcher sUriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    public static final int PETS=100;

    public static final int PETS_ID=101;

    static {
        sUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY,PetsContract.path,PETS);

        sUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY,PetsContract.path+"#",PETS_ID);
    }

    PetDBHelper dbHelper;
    @Override
    public boolean onCreate() {
        dbHelper=new PetDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database=dbHelper.getReadableDatabase();

        int match=sUriMatcher.match(uri);
        Cursor cu;

        switch (match){

            case PETS:
                cu=database.query(PetsEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case PETS_ID:
                selection=PetsEntry.COLUMN_ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cu=database.query(PetsContract.PetsEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);

        }
        return cu;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        int match=sUriMatcher.match(uri);

        switch (match){
            case PETS:
                return PetsEntry.CONTENT_LIST_TYPE;
            case PETS_ID:
                return PetsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI"+uri+"with match"+match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match=sUriMatcher.match(uri);

        switch (match){
            case PETS:
              return insertPet(uri,values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database=dbHelper.getWritableDatabase();
        int match=sUriMatcher.match(uri);
        switch (match){
            case PETS:
                return database.delete(PetsEntry.TABLE_NAME,selection,selectionArgs);
            case PETS_ID:
                selection=PETS_ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                return database.delete(PetsEntry.TABLE_NAME,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported by"+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match=sUriMatcher.match(uri);

        switch (match){
            case PETS:
                return updatePet(uri,values,selection,selectionArgs);
            case PETS_ID:
                selection=PETS_ID+"=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri,values,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Updatation s not supported for"+uri);
        }

    }

    public int updatePet(Uri uri,ContentValues values,String selection,String[] selectionArgs){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        String name=values.getAsString(PetsEntry.COLUMN_PET_NAME);
        if(name==null){
            throw new IllegalArgumentException("Pet requires a name");
        }
        Integer gender=values.getAsInteger(PetsEntry.COLUMN_PET_GENDER);
        if (gender == null || PetsEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Pets Required a gender");
        }
        Integer weight=values.getAsInteger(PetsEntry.COLUMN_PET_WEIGHT);
        if(weight!=null && weight<0){
            throw new IllegalArgumentException("Enter the valid weight");
        }
        if(values.size()==0){
            return 0;
        }
        int rowId=database.update(PetsEntry.TABLE_NAME,values,selection,selectionArgs);
        if(rowId==0){
            Log.e(LOG_TAG,"Failed to update row for"+uri);
            return 0;
        }
        return rowId;
    }

    public Uri insertPet(Uri uri,ContentValues values){
        SQLiteDatabase database=dbHelper.getWritableDatabase();
        String name=values.getAsString(PetsEntry.COLUMN_PET_NAME);
        if(name==null){
            throw new IllegalArgumentException("Pet requires a name");
        }
        Integer gender=values.getAsInteger(PetsEntry.COLUMN_PET_GENDER);
        if (gender == null || PetsEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Pets Required a gender");
        }
        Integer weight=values.getAsInteger(PetsEntry.COLUMN_PET_WEIGHT);
        if(weight!=null && weight<0){
            throw new IllegalArgumentException("Enter the valid weight");
        }
        long rowId=database.insert(PetsEntry.TABLE_NAME,null,values);
        if(rowId==-1){
            Log.e(LOG_TAG,"Failed to insert row for"+uri);
            return null;
        }
        Uri nuri=ContentUris.withAppendedId(uri,rowId);
        return nuri;
    }
}
