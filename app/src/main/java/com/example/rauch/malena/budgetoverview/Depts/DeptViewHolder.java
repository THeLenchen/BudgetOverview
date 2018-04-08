package com.example.rauch.malena.budgetoverview.Depts;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.ClickListener;
import com.example.rauch.malena.budgetoverview.R;

public class DeptViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{

    public TextView mFriend;
    public TextView mAmount;
    private ClickListener mClickListener;
    private Cursor mCursor;

    public DeptViewHolder(View itemView) {

        super(itemView);
        mFriend =  itemView.findViewById(R.id.dept_item_name);
        mAmount = itemView.findViewById(R.id.dept_item_amount);
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
        mClickListener.reminderonLongClick(id);
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
        mClickListener.reminderonClick(id);
    }

    public void swapCursor(Cursor newCursor) {

        if (mCursor != null) mCursor.close();

        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            //this.notifyDataSetChanged();
        }
    }
}
