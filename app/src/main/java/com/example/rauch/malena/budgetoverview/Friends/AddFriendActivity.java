package com.example.rauch.malena.budgetoverview.Friends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.rauch.malena.budgetoverview.Database.DataSource;
import com.example.rauch.malena.budgetoverview.R;

public class AddFriendActivity extends AppCompatActivity {

    private DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //initialise and open DataSource
        mDataSource = new DataSource(this);
        mDataSource.open();

        ImageButton save = findViewById(R.id.imageButton_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mDataSource.close();
                finish();
            }
        });
        ImageButton delete = findViewById(R.id.imageButton_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSource.close();
                finish();
            }
        });
    }
}
