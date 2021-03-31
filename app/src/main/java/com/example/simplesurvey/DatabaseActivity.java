package com.example.simplesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DB_MESSAGE);

        //Get the display TextView for this activity and display the message
        TextView dbTV = findViewById(R.id.dbTextViewID);
        dbTV.setText(message);
    }

    /**Callback when user selects the "BACK" button*/
    public void goBackHandler(View view){
        //Close the activity
        finish();
    }

}
