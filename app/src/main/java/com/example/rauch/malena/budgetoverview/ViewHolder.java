package com.example.rauch.malena.budgetoverview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Test on 28.03.2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    public TextView textView;
    private ClickListener mClickListener;

    public ViewHolder(View itemView, ClickListener mTransactiClickListener) {
        super(itemView);
        textView= itemView.findViewById(android.R.id.text1);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        int clickedPosition = getAdapterPosition();
        mClickListener.transactionOnClick(clickedPosition);
        return true;

    }
}
