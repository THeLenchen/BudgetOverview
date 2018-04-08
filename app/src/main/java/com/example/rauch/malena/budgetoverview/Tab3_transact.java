package com.example.rauch.malena.budgetoverview;


import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.Transaction.TransactionAdapter;


public class Tab3_transact extends Fragment implements ClickListener {


    //locale Variables
    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mTransactionRecyclerView;
    // Database related to Database
    private Cursor mCursor;
    private DataSource mDataSource;
    //Constants used when calling the update activity
    public static final String TRANSACTION_POSITION = "Position";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.tap3_transakt, container, false);

        //initialise DataSource for database operations
        mDataSource = new DataSource(rootView.getContext());
        mDataSource.open();

        //initialise RecyclerView, set Layout, set Adapter and define Transaction layout
        mTransactionRecyclerView = rootView.findViewById(R.id.tab3_RecyclerView_Transakt);
        mTransactionRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mTransactionAdapter = new TransactionAdapter(mCursor, this.getContext());
        mTransactionRecyclerView.setAdapter(mTransactionAdapter);


        //initialise the floatingbutton
        FloatingActionButton button = rootView.findViewById(R.id.transaction_floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddTransactActivity.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        return;
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mTransactionRecyclerView);

        updateUI();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataSource.open();
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mCursor != null && !mCursor.isClosed()) mCursor.close();
        mDataSource.close();
    }


    private void updateUI() {
        mCursor = mDataSource.getAllTransactions();
        if (mTransactionAdapter == null) {
            mTransactionAdapter = new TransactionAdapter(mCursor, this.getContext());
            mTransactionRecyclerView.setAdapter(mTransactionAdapter);
        } else {
            mTransactionAdapter.swapCursor(mCursor);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reminderonClick(long id) {
        //Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        //intent.putExtra(TRANSACTION_POSITION, id);
        //startActivity (intent);
    }

    @Override
    public void reminderonLongClick(long id) {
       // Uri singleUri = ContentUris.withAppendedId(ReminderContract.CONTENT_URI,id);
       // getContentResolver().delete(singleUri, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Object> loader, Object data) {    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Object> loader) {    }

    @Override
    public void transactionOnLongClick(long id) {    }

    @Override
    public void transactionOnClick(long id) {    }
}
