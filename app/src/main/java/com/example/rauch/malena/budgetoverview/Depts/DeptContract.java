package com.example.rauch.malena.budgetoverview.Depts;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.rauch.malena.budgetoverview.Transaction.TransactionContract;

/**
 * Created by Test on 27.03.2018.
 */

public class DeptContract {

    //works the same as a URL, so this is the way to access tables in Contentprovider
    public static final String AUTHORITY = "com.example.provider.depts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY  + "/" + DeptContract.DeptEntry.TABLE_NAME);

    private DeptContract() {}

    /* Inner class that defines the table contents */
    public static class DeptEntry implements BaseColumns {
        public static final String TABLE_NAME = "Depts";
        public static final String COLUMN_NAME_DEPT = "dept";
        public static final String COLUMN_NAME_AMAOUNT = "amount";
        public static final String COLUMN_NAME_FRIEND = "friend";
    }
}