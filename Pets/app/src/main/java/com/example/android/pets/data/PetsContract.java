package com.example.android.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class PetsContract {

    private PetsContract(){}

    public static final String CONTENT_AUTHORITY="com.example.android.pets";

    public static final Uri BASE_URI=Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String path="pets";

    public static final class PetsEntry implements BaseColumns{

        public static final Uri CONTENT_URI=Uri.withAppendedPath(BASE_URI,path);

        public static final String TABLE_NAME="Pets";

        public static final String COLUMN_ID=BaseColumns._ID;
        public static final String COLUMN_PET_NAME="name";
        public static final String COLUMN_PET_BREED="breed";
        public static final String COLUMN_PET_GENDER="gender";
        public static final String COLUMN_PET_WEIGHT="weight";

        public static final int GENDER_MALE=1;
        public static final int GENDER_FEMALE=2;
        public static final int GENDER_UNKNOWN=0;
    }
}
