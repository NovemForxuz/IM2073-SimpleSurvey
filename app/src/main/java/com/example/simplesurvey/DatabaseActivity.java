package com.example.simplesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

public class DatabaseActivity extends AppCompatActivity {
    SimpleAdapter AD;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DB_MESSAGE);

        //Get the display TextView for this activity and display the message
        TextView dbTV = findViewById(R.id.dbTextViewID);
        dbTV.setText(message);

        ListView LV_Data = findViewById(R.id.LV_data);
        Button qryBtn = findViewById(R.id.btnQueryID);
        qryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Map<String,String>> MydataList=null;
                GetData myData = new GetData();
                MydataList = myData.getdata();

                String[] fromwhere = {"questionNO", "question", "answerA", "answerB", "answerC", "answerD", "time", "Description"};

                int[] viewwhere = {R.id.qnNoID,R.id.qnID,R.id.aID,R.id.bID,R.id.cID,R.id.dID,R.id.timeID,R.id.descID};
                AD = new SimpleAdapter(DatabaseActivity.this,MydataList,R.layout.listtemplate,fromwhere,viewwhere);
                LV_Data.setAdapter(AD);
            }
        });
    }

    /**Callback when user selects the "BACK" button*/
    public void goBackHandler(View view){
        //Close the activity
        finish();
    }

}
