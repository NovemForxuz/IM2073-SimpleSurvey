/*-----PRESENTATION LAYER-----*/
package com.example.simplesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**-----Communications-----*/
/*CompleteActivity ---> DatabaseActivity
* CompleteActivity <--- MainActivity
*/

 // TODO: Complete Page upon answering all questions

public class CompleteActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        //Get the message from the intent (DatabaseActivity)
        Intent intent = getIntent();
        String message = intent.getStringExtra(DatabaseActivity.DB_COMPLETE_MESSAGE);


    }

    /*Callback when user selects the "BACK" button*/
    public void goBackHandler(View view){
        //Close the activity
        finish();
    }
}
