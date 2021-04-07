/*-----PRESENTATION LAYER (Main)-----*/
//should be as dumb as possible (no business logic)

package com.example.simplesurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//TODO: Starting page user interface

public class MainActivity extends AppCompatActivity {
    protected static final String DB_MESSAGE = "database";
    protected  static final String USERNAME = "username";

    EditText userTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTV = findViewById(R.id.userTvID);

        //Get questions from db
        downloadJSON();
    }

    /**Callback when the user selects the "Database" button*/
    public void goDatabaseHandler(View view){

        if (userTV.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter a name", Toast.LENGTH_LONG).show();
        }else{
            //Create an intent for the second activity
            Intent intent = new Intent(this, DatabaseActivity.class);
            String message = "- Database -";
            intent.putExtra(DB_MESSAGE, message);   //key-value pair
            String username = userTV.getText().toString();
            intent.putExtra(USERNAME, username);
            //Start the intended activity
            startActivity(intent);
        }
    }

    /**Downloading db content form web*/
    private void downloadJSON(){
        //Connection to retrieve db query
        ConnectionHelper getJSON = new ConnectionHelper();
        getJSON.execute();
    }
}