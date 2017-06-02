package com.ydkmm.sqlitetestforalarm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asrafkaizen on 6/2/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    // The database name
    private static final String DATABASE_NAME = "alarms.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "alarms_table";
    public static final String COL_ID = "col_id";
    public static final String COL_TIME = "col_time";
    public static final String COL_DETAILS = "col_details";

    // Constructor
    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold waitlist data
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TIME + " TEXT NOT NULL, " +
                COL_DETAILS + " INTEGER NOT NULL, " +
                "); ";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}