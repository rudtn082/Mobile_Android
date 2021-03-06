package com.example.kyungsoo.mp01_09_201604140;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Movies.db";
    public static final String MOVIEWS_TABLE_NAME = "movies";
    public static final String MOVIEWS_COLUMN_NAME = "name";
    public static final String MOVIEWS_COLUMN_YEAR = "year";
    public static final String MOVIEWS_COLUMN_DIRECTOR = "director";
    public static final String MOVIEWS_COLUMN_RATE = "rate";
    public static final String MOVIEWS_COLUMN_COUNTRY = "country";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table movies " + "(_id INTEGER primary key, name text, year integer, director text, rate integer, country text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
