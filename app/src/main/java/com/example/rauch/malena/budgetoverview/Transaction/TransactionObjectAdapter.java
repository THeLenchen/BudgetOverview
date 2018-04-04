package com.example.rauch.malena.budgetoverview.Transaction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rauch.malena.budgetoverview.R;

import java.util.List;

/**
 * Created by Test on 03.04.2018.
 */

public class TransactionObjectAdapter extends RecyclerView.Adapter<TransactionObjectViewHolder> {

    private Context context;
    public List<Transaction> listTransactionObject;

    public TransactionObjectAdapter(Context context, List<Transaction> listTransactionObject) {
        this.context = context;
        this.listTransactionObject = listTransactionObject;
    }

    //create a viewHolder for the transaction Item
    @Override
    public TransactionObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transact_recyclerview_item, parent, false);
        return new TransactionObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionObjectViewHolder holder, int position) {
        // Gets a single item in the list from its position
        final Transaction transactionObject = listTransactionObject.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.mName.setText(transactionObject.getmName());
        holder.mAmount.setText(transactionObject.getmAmount().toString());
        // The whole layout is used for the onClickListener instead of individual views
        // inside the viewHolder
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start activity
            }
        });
    }

    @Override
    public int getItemCount() {
        return  listTransactionObject.size();
    }
}
