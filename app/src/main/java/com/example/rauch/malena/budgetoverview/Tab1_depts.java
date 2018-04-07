package com.example.rauch.malena.budgetoverview;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.Depts.Dept;
import com.example.rauch.malena.budgetoverview.Depts.DeptAdapter;

public class Tab1_depts extends Fragment implements ClickListener, DeptAdapter.DeptClickListener{

    private DeptAdapter mDeptAdapter;
    private RecyclerView mDeptRecyclerView;
    //Constants used when calling the update activity
    public static final String DEPT_POSITION = "Position";
    //Database related local variables
    private Cursor mCursor;
    private DataSource mDataSource;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tap1_depts, container, false);

        mDeptRecyclerView = rootView.findViewById(R.id.tab1_RecyclerView);
        //mTransactionRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        mDeptRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        mDeptAdapter = new DeptAdapter(this, mCursor);
        mDeptRecyclerView.setAdapter(mDeptAdapter);

        mDataSource = new DataSource(rootView.getContext());
        mDataSource.open();


        //initialise the floatingbutton
        FloatingActionButton button = rootView.findViewById(R.id.tap1_floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getContext(), AddTransactActivity.class);
                //startActivity(intent);

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
                        //       mReminders.remove(position);
                        mDeptAdapter.notifyItemRemoved(position);
                    }
                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mDeptRecyclerView);

        updateUI();

        return rootView;
    }

    private void updateUI() {
        mCursor = mDataSource.getAllDepts();
        if (mDeptAdapter == null) {
            mDeptAdapter = new DeptAdapter(this, mCursor);
            mDeptRecyclerView.setAdapter(mDeptAdapter);
        } else {
            mDeptAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void reminderonClick(long id) {    }

    @Override
    public void reminderonLongClick(long id) {    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {    }

    @Override
    public void transactionOnLongClick(long id) {    }

    @Override
    public void transactionOnClick(long id) {    }

    @Override
    public void reminderOnClick(long id) {

    }

    @Override
    public void reminderOnLongClick(long id) {
        mDataSource.deleteDept(id);
        updateUI();
    }
}
