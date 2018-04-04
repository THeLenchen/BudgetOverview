package com.example.rauch.malena.budgetoverview.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rauch.malena.budgetoverview.Transaction.TransactionContract;

/**
 * Created by Test on 15.03.2018.
 */

public class DataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] TRANSACTIONS_ALL_COLUMNS = {TransactionContract.TransactionEntry._ID,
            TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION,
            //TransactionContract.TransactionEntry.COLUMN_AMOUNT_TRANSACTION,
            //TransactionContract.TransactionEntry.COLUMN_SPENT_TRANSACTION
    };

    public DataSource(Context context) {
        mContext = context;
        mDBHelper = new DBHelper(mContext);
    }

    // Opens the database to use it
    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        mDBHelper.close();
    }

    public void createTransaction(String trasnactionName, double trasnactionAmount, boolean trasnactionSpent) {
        ContentValues values = new ContentValues();
        values.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION, trasnactionName);
        //values.put(TransactionContract.TransactionEntry.COLUMN_AMOUNT_TRANSACTION, trasnactionAmount);
        //values.put(TransactionContract.TransactionEntry.COLUMN_SPENT_TRANSACTION, trasnactionSpent);
        mDatabase.insert(TransactionContract.TransactionEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllTransactions() {
        return mDatabase.query(TransactionContract.TransactionEntry.TABLE_NAME,
                TRANSACTIONS_ALL_COLUMNS, null, null, null, null, null);
    }

    public void deleteTransaction(long id) {
        mDatabase.delete(TransactionContract.TransactionEntry.TABLE_NAME, TransactionContract.TransactionEntry._ID + " =?",
                new String[]{Long.toString(id)});

    }

    public void updateTransaction(long id, String name) {
        ContentValues args = new ContentValues();
        args.put(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION, name);

        mDatabase.update(TransactionContract.TransactionEntry.TABLE_NAME, args, TransactionContract.TransactionEntry._ID + "=?",
                new String[]{Long.toString(id)});
    }

    public Cursor getOneTransaction(long id) {
        return mDatabase.query(TransactionContract.TransactionEntry.TABLE_NAME, TRANSACTIONS_ALL_COLUMNS, TransactionContract.TransactionEntry._ID + " =?", new String[]{Long.toString(id)}, null, null, null);
    }

}
