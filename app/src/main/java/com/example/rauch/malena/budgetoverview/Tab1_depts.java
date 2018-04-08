package com.example.rauch.malena.budgetoverview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.Depts.DeptAdapter;

public class Tab1_depts extends Fragment implements ClickListener, DeptAdapter.DeptClickListener {

    private DeptAdapter mDeptAdapter;
    private RecyclerView mDeptRecyclerView;
    //Constants used when calling the update activity
    public static final String DEPT_POSITION = "Position";
    //Database related local variables
    private Cursor mCursor;
    private DataSource mDataSource;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tap1_depts, container, false);

        //initialise RecyclerView, set Layout, set Adapter and define Dept layout
        mDeptRecyclerView = rootView.findViewById(R.id.tab1_RecyclerView);
        mDeptRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mDeptAdapter = new DeptAdapter(this.getContext(), mCursor);
        mDeptRecyclerView.setAdapter(mDeptAdapter);

        //initialise DataSource for database operations
        mDataSource = new DataSource(rootView.getContext());
        mDataSource.open();


        //initialise the floatingbutton
        FloatingActionButton button = rootView.findViewById(R.id.tap1_floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddDeptActivity.class);
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
                        //Remove from Database
                        mDeptAdapter.notifyItemRemoved(position);
                    }
                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mDeptRecyclerView);

        updateUI();

        return rootView;
    }

    //Updates the UI
    private void updateUI() {
        mCursor = mDataSource.getAllDepts();
        if (mDeptAdapter == null) {
            mDeptAdapter = new DeptAdapter(this.getContext(), mCursor);
            mDeptRecyclerView.setAdapter(mDeptAdapter);
        } else {
            mDeptAdapter.swapCursor(mCursor);
        }

        TextView budget = rootView.findViewById(R.id.tab1_textView_budget);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("test", 0);
        float budgetString = sharedPreferences.getFloat("budget", 00.00f);
        budget.setText(String.valueOf(budgetString));

    }

    //update UI and open DataSource when returning from an activity
    @Override
    public void onResume() {
        super.onResume();
        mDataSource.open();
        updateUI();
    }

    @Override
    public void reminderonClick(long id) {
    }

    @Override
    public void reminderonLongClick(long id) {
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
    }

    @Override
    public void transactionOnLongClick(long id) {
    }

    @Override
    public void transactionOnClick(long id) {
    }

    @Override
    public void reminderOnClick(long id) {
    }

    @Override
    public void reminderOnLongClick(long id) {
        mDataSource.deleteDept(id);
        updateUI();
    }
}
