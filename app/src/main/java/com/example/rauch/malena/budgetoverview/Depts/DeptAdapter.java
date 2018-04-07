package com.example.rauch.malena.budgetoverview.Depts;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DeptAdapter extends RecyclerView.Adapter<DeptAdapter.ViewHolder> {

    final private DeptClickListener mDeptClickListener;
    private Cursor mCursor;

    public interface DeptClickListener {
        void reminderOnClick (long id);
        void reminderOnLongClick (long id);
    }

    public DeptAdapter(DeptClickListener mReminderClickListener, Cursor mCursor) {
        this.mDeptClickListener = mReminderClickListener;
        this.mCursor = mCursor;
    }

    @Override
    public DeptAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

// Return a new holder instance
        DeptAdapter.ViewHolder viewHolder = new DeptAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DeptAdapter.ViewHolder holder, final int position) {

        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        String name = mCursor.getString(mCursor.getColumnIndex(DeptContract.DeptEntry.COLUMN_NAME_DEPT));

        holder.textView.setText(name);

    }

    @Override
    public int getItemCount() {
        return (mCursor == null ? 0 : mCursor.getCount());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView) {

            super(itemView);
            textView=  itemView.findViewById(android.R.id.text1);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {



            int clickedPosition =  getAdapterPosition();
            if (!mCursor.moveToPosition(clickedPosition))
                return false; // bail if returned null
            // Update the view holder with the information needed to display
            final long id =
                    mCursor.getLong(mCursor.getColumnIndex(DeptContract.DeptEntry._ID));
            mDeptClickListener.reminderOnLongClick(id);
            return true;
        }

        @Override
        public void onClick(View view) {
            int clickedPosition =  getAdapterPosition();
            if (!mCursor.moveToPosition(clickedPosition))
                return; // bail if returned null
            // Update the view holder with the information needed to display
            final long id =
                    mCursor.getLong(mCursor.getColumnIndex(DeptContract.DeptEntry._ID));
            mDeptClickListener.reminderOnClick(id);
        }
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
