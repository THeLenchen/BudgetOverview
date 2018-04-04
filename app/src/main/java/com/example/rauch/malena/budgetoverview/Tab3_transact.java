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
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.Transaction.Transaction;
import com.example.rauch.malena.budgetoverview.Transaction.TransactionAdapter;
import com.example.rauch.malena.budgetoverview.Transaction.TransactionObjectAdapter;

import java.util.ArrayList;
import java.util.List;


public class Tab3_transact extends Fragment implements ClickListener {


    private TransactionAdapter mTransactionAdapter;
    private RecyclerView mTransactionRecyclerView;
    private List<Transaction> mTransactions;
    public static final String TRANSACTION_POSITION = "Position";
    private TextView mNewTransaction;
    private Cursor mCursor;
    private DataSource mDataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tap3_transakt, container, false);

        mTransactions = new ArrayList<>();

        //initialise RecyclerView, set Layout, set Adapter and define Transaction layout
        mTransactionRecyclerView = rootView.findViewById(R.id.tab3_RecyclerView_Transakt);
        mTransactionRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        TransactionObjectAdapter objectAdapter = new TransactionObjectAdapter(this.getContext(), mTransactions);
        mTransactionRecyclerView.setAdapter(objectAdapter);
        mNewTransaction = rootView.findViewById(R.id.editText_test);

        //initialise DataSource for database operations
        mDataSource = new DataSource(rootView.getContext());
        mDataSource.open();

        //initialise the floatingbutton
        FloatingActionButton button = rootView.findViewById(R.id.floatingActionButton2_test);
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

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());
                        mTransactions.remove(position);
                        mTransactionAdapter.notifyItemRemoved(position);
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mTransactionRecyclerView);

        updateUI();

        return rootView;
    }

    public DataSource getmDataSource(){
        return mDataSource;
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
            mTransactionAdapter = new TransactionAdapter(mCursor, mTransactions, this);
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
    public void onLoadFinished(android.support.v4.content.Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Object> loader) {

    }

    @Override
    public void transactionOnLongClick(long id) {

    }

    @Override
    public void transactionOnClick(long id) {

    }
}
