package com.example.rauch.malena.budgetoverview.Transaction;

public class Transaction {

    private boolean mSpent;
    private String mName;
    private double mAmount;

    public Transaction(Boolean spent, String name, double amount) {
        mSpent = spent;
        mName = name;
        mAmount = amount;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Double getmAmount() {
        return mAmount;
    }

    public void setmAmount(Double mAmount) {
        this.mAmount = mAmount;
    }

    public boolean ismSpent() {
        return mSpent;
    }

    public void setmSpent(boolean mSpent) {
        this.mSpent = mSpent;
    }
}
