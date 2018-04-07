package com.example.rauch.malena.budgetoverview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.Database.DataSource;

public class AddTransactActivity extends AppCompatActivity {

    private DataSource mDataSource;
    private long mID;
    private RadioButton mSpent;
    private RadioButton mGet;
    private TextView mName;
    private TextView mAmount;
    private boolean mBooleanSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transakt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Obtain parameters providet by Tab3_transact
        mID = getIntent().getLongExtra(Tab3_transact.TRANSACTION_POSITION, -1);

        //initialise and open DataSource
        mDataSource = new DataSource(this);
        mDataSource.open();

        mSpent = findViewById(R.id.addTransaction_radioButton_give);
        mGet = findViewById(R.id.addTransaction_radioButton_get);
        mAmount = findViewById(R.id.addTransaction_editText_amount);
        mName = findViewById(R.id.addTransaction_editText_name);


        ImageButton save = findViewById(R.id.addTransaction_imageButton_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean goOn = true;
                if (mSpent.isChecked()) {
                    mBooleanSpent = true;
                } else {
                    mBooleanSpent = false;
                }

                if (goOn == true) {
                    double amount = 0;

                    try {
                        amount = Double.parseDouble(mAmount.getText().toString());
                    } catch (NumberFormatException nfe) {
                        Snackbar.make(view, "Use only numbers an '.' for Amount", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        goOn = false;
                    }

                    if (goOn == true) {
                        String name = mName.getText().toString();
                        mDataSource.createTransaction(name, amount, mBooleanSpent);
                        mDataSource.close();
                        finish();
                    }
                }
            }
        });
        ImageButton delete = findViewById(R.id.addTransaction_imageButton_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSource.close();
                finish();
            }
        });
    }

}
