/*-----PRESENTATION LAYER (DB)-----*/
//should be as dumb as possible (no business logic)

package com.example.simplesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesurvey.Model.QuestionaireViewModel;

/**-----Communications-----*/
/*DBactivity <--> QuestionaireVM
*
*/
public class DatabaseActivity extends AppCompatActivity {

    //Declare UI objects
    TextView qnTv, qnNoTv;
    Button btnA, btnB, btnC, btnD;

    //Declare Communication classes
    QuestionaireViewModel qvm;

    //Intent message
    protected static final String DB_COMPLETE_MESSAGE = "database";

    private int qnIndex = 0;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Get the message from the intent (MainActivity)
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DB_MESSAGE);

        //Get the display TextView for this activity and display the message
        TextView dbTV = findViewById(R.id.dbQnNoID);
        dbTV.setText(message);

        //Retrieve database queries
        downloadJSON();

        //Construct QuestionaireViewModel
        qvm = new QuestionaireViewModel();

        /*Bind UI objects and initialize UIs*/
        qnNoTv = findViewById(R.id.dbQnNoID);
        qvm.setQuestionNo(qnIndex);
        qnNoTv.setText("Question " + qvm.getQuestionNo() + " of " + qvm.getTotalQns());

        qnTv = findViewById(R.id.dbQnTVID);
        qvm.setQn(qnIndex);
        qnTv.setText(qvm.getQuestion());

        btnA = findViewById(R.id.btnAnsAID);
        qvm.setAnswerA(qnIndex);
        btnA.setText(qvm.getAnswerA());

        btnB = findViewById(R.id.btnAnsBID);
        qvm.setAnswerB(qnIndex);
        btnB.setText(qvm.getAnswerB());

        btnC = findViewById(R.id.btnAnsCID);
        qvm.setAnswerC(qnIndex);
        btnC.setText(qvm.getAnswerC());

        btnD = findViewById(R.id.btnAnsDID);
        qvm.setAnswerD(qnIndex);
        btnD.setText(qvm.getAnswerD());

        //debug
        qvm.logQuestionSet();
    }

    /*Callback when user select the answer buttons*/
    public void loadQuestions(View view){
        qnIndex++;
        if(qnIndex < qvm.getTotalQns()){
            //Re-initialize UIs
            try {
                qvm.setQuestionNo(qnIndex);
                qvm.setQn(qnIndex);
                qvm.setAnswerA(qnIndex);
                qvm.setAnswerB(qnIndex);
                qvm.setAnswerC(qnIndex);
                qvm.setAnswerD(qnIndex);
            }catch (Exception e){
                Log.e("Btn get callback", e.getMessage());
                qnIndex--;
            }
            try {
                qnNoTv.setText("Question " + qvm.getQuestionNo() + " of " + qvm.getTotalQns());
                qnTv.setText(String.valueOf(qvm.getQuestion()));
                btnA.setText(String.valueOf(qvm.getAnswerA()));
                btnB.setText(String.valueOf(qvm.getAnswerB()));
                btnC.setText(String.valueOf(qvm.getAnswerC()));
                btnD.setText(String.valueOf(qvm.getAnswerD()));
            }catch (Exception e){
                Log.e("Btn set callback", e.getMessage());
                qnIndex--;
            }
        }else{
            //Create an intent for the second activity
            Intent intent = new Intent(this, CompleteActivity.class);
            String message = "- Database -";
            intent.putExtra(DB_COMPLETE_MESSAGE, message);   //key-value pair
            //Start the intended activity
            startActivity(intent);
        }

    }

    /*Downloading db content form web*/
    private void downloadJSON(){
        //Connection to retrieve db query
        ConnectionHelper getJSON = new ConnectionHelper();
        getJSON.execute();
    }

    /*Callback when user selects the "BACK" button*/
    public void goBackHandler(View view){
        //Close the activity
        finish();
    }
}
