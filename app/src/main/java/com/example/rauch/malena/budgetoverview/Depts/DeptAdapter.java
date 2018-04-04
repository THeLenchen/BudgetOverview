package com.example.rauch.malena.budgetoverview.Depts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.ClickListener;
import com.example.rauch.malena.budgetoverview.Tab1_depts;
import com.example.rauch.malena.budgetoverview.ViewHolder;

import java.util.List;

/**
 * Created by Test on 27.03.2018.
 */

public class DeptAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Dept> mDepts;
    final private ClickListener mDeptClickListener;
    public static final String REMINDER_POSITION = "Position";

    //Constructor
  public DeptAdapter(List<Dept> mDepts, ClickListener clickListener) {
        this.mDepts = mDepts;
        this.mDeptClickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view, mDeptClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dept dept = mDepts.get(position);
        holder.textView.setText(dept.getText());
    }

    @Override
    public int getItemCount() {
        return mDepts.size();
    }
}
