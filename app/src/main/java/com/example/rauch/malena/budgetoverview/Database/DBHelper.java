package com.example.rauch.malena.budgetoverview.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rauch.malena.budgetoverview.Transaction.TransactionContract;

/**
 * Created by Test on 15.03.2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_FIRST_NAME = "budgetOverview";
    public static final String DATABASE_NAME_EXTENSION = ".db";
    public static final String DATABASE_NAME = DATABASE_FIRST_NAME + DATABASE_NAME_EXTENSION;
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mSqLiteDatabase;

    //Create the table
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TransactionContract.TransactionEntry.TABLE_NAME +
                    "(" +
                    TransactionContract.TransactionEntry._ID + " INTEGER PRIMARY KEY, " +
                    TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION +
                    ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // this.mSqLiteDatabase = sqLiteDatabase;
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mSqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +
                TransactionContract.TransactionEntry.TABLE_NAME);
        onCreate(mSqLiteDatabase);
    }
}
