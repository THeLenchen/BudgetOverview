package com.example.rauch.malena.budgetoverview.Depts;

/**
 * Created by Test on 27.03.2018.
 */

public class Dept {
    private String text;

    public Dept(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
