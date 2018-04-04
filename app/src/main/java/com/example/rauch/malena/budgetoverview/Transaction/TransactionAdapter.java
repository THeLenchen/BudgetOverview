package com.example.rauch.malena.budgetoverview.Transaction;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.ClickListener;
import com.example.rauch.malena.budgetoverview.ViewHolder;

import java.util.List;

/*
Adapter Class for the transaktions


public class TransactionAdapter extends RecyclerView.Adapter<ViewHolder> {

     private Cursor mCursor;
    private List<Transaction> mTransactions;
    final private ClickListener mTransactiClickListener;

    //Constructor
    public TransactionAdapter(Cursor cursor, List<Transaction> transaktions, ClickListener mTransactiClickListener) {
        this.mCursor = cursor;
        this.mTransactions = transaktions;
        this.mTransactiClickListener = mTransactiClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view, mTransactiClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        String name = mCursor.getString(mCursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_NAME_TRANSACTION));

       holder.textView.setText(name);
    }

    @Override
    public int getItemCount() {
        return (mCursor == null ? 0 : mCursor.getCount());
        //return mTransactions.size();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

}
*/