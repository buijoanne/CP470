package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    static final int VERSION_NUM = 2;
    public static final String KEY_ID = "KEY_ID";
    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    public static final String TABLE_NAME = "RECORDS";


    public ChatDatabaseHelper(Context ctx){
        super(ctx,DATABASE_NAME, null, VERSION_NUM);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create a table with a column for id of integers that autoincrement and a column for MESSAGE as strings.
        db.execSQL(DATABASE_CREATE);
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + KEY_ID + " integer primary key autoincrement, " + KEY_MESSAGE+ " text not null);";
    private static final String t = ("DROP TABLE IF EXISTS " + TABLE_NAME);

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(t);
        onCreate(db);
        Log.i("ChatDatabaseHelper", " Calling onUpgrade, oldVersion= " + oldVer + " newVersion= " +newVer);
    }
}
