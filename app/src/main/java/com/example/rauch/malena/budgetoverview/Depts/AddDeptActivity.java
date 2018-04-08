package com.example.rauch.malena.budgetoverview.Depts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.R;
import com.example.rauch.malena.budgetoverview.Tab3_transact;

//Java Class for the activity to add a Dept
public class AddDeptActivity extends AppCompatActivity {

    private DataSource mDataSource;
    private long mID;
    private RadioButton mGive;
    private RadioButton mGet;
    private TextView mName;
    private TextView mAmount;
    private int mBooleanGet;
    //Constants used for SharedPreference
    private static final String FILENAME = "test";
    private static final String VAL_KEY_BUDGET = "budget";
    private static final String VAL_KEY_GIVE_TO = "giveTo";
    private static final String VAL_KEY_GET_FROM = "getFrom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dept);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Obtain parameters providet by Tab3_transact
        mID = getIntent().getLongExtra(Tab3_transact.TRANSACTION_POSITION, -1);

        //initialise and open DataSource
        mDataSource = new DataSource(this);
        mDataSource.open();

        mGive = findViewById(R.id.addDept_radioButton_give);
        mGet = findViewById(R.id.addDept_radioButton_get);
        mAmount = findViewById(R.id.addDept_editText_amount);
        mName = findViewById(R.id.addDept_editText_friend);

        ImageButton save = findViewById(R.id.addDept_imageButton_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean goOn = true;
                if (mGive.isChecked()) {
                    mBooleanGet = 1;
                } else if (mGet.isChecked()) {
                    mBooleanGet = 0;
                } else {
                    Snackbar.make(view, "You have to select get or give", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
                        mDataSource.createDept(name, amount, mBooleanGet);

                        SharedPreferences sharedPreferences = getSharedPreferences(FILENAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Edit Budget
                        float tempBudget = sharedPreferences.getFloat(VAL_KEY_BUDGET, 00.00f);
                        double newBudget = tempBudget + mDataSource.getDeptAmountOfLatestEntry();
                        String budgetString = Double.toString(newBudget);
                        editor.putFloat("budget", Float.valueOf(budgetString));

                        //Edit get
                        float tempGet = sharedPreferences.getFloat(VAL_KEY_BUDGET, 00.00f);
                        double newGet = tempGet + mDataSource.getDeptAmountOfLatestEntry();
                        String getString = Double.toString(newGet);
                        editor.putFloat("budget", Float.valueOf(getString));

                        //Edit give
                        float tempGive = sharedPreferences.getFloat(VAL_KEY_BUDGET, 00.00f);
                        double newGive = tempGive + mDataSource.getDeptAmountOfLatestEntry();
                        String giveString = Double.toString(newGive);
                        editor.putFloat("budget", Float.valueOf(giveString));

                        editor.commit();
                        editor.apply();
                        mDataSource.close();
                        finish();
                    }
                }
            }
        });

        //return if delete button is pressed
        ImageButton delete = findViewById(R.id.addDept_imageButton_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSource.close();
                finish();
            }
        });
    }

}
