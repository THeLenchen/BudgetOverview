package com.example.rauch.malena.budgetoverview.Transaction;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Test on 27.03.2018.
 */

public class TransactionContract {

    //works the same as a URL, so this is the way to access tables in Contentprovider
    public static final String AUTHORITY = "com.example.provider.transactions";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY  + "/" + TransactionContract.TransactionEntry.TABLE_NAME);

    private TransactionContract() {}

    /* Inner class that defines the table contents */
    public static class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME = "Transactions";
        public static final String COLUMN_NAME_TRANSACTION = "budgetTransaction";
        public static final String COLUMN_AMOUNT_TRANSACTION = "budgetTransaction";
        public static final String COLUMN_SPENT_TRANSACTION = "budgetTransaction";

    }
}
