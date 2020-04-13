package com.example.tutorial5;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.tutorial5.Constants.TABLE_NAME;
import static com.example.tutorial5.Constants.TIME;
import static com.example.tutorial5.Constants.TITLE;

public class EventsData extends SQLiteOpenHelper {
    private static final String DATABSE_NAME = "events.db";
    private static final int DATABSE_VERSION = 1;

    public EventsData(Context ctx){
        super(ctx, DATABSE_NAME, null , DATABSE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + " INTEGER,"
                + TITLE + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
