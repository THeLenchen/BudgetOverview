package com.example.rauch.malena.budgetoverview.Depts;

/**
 * Created by Test on 27.03.2018.
 */

public class Dept {
    private String friend;

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private boolean loan;
    private double amount;

    public Dept(String text) {
        friend = text;
    }

    public String getText() {
        return friend;
    }

    @Override
    public String toString() {
        return friend;
    }
}
