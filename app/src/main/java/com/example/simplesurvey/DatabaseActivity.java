/*-----PRESENTATION LAYER (DB)-----*/
//should be as dumb as possible (no business logic)

package com.example.simplesurvey;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplesurvey.Model.AnswernaireViewModel;
import com.example.simplesurvey.Model.QuestionaireViewModel;
import com.example.simplesurvey.Service.ConnectionHelper;
import com.example.simplesurvey.Service.ConnectionInsert;

import java.security.spec.ECField;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**-----Communications-----*/
/*DBactivity <--> QuestionaireVM
* DBactivity ---> AnswernaireVM
*/

public class DatabaseActivity extends AppCompatActivity {

    //Declare UI objects
    private TextView qnTv, qnNoTv;
    private Button btnA, btnB, btnC, btnD, btnNext;
    private ProgressBar progBar;
    private String username, time;
    private boolean isValid;

    //Declare Communication classes
    QuestionaireViewModel qvm;
    AnswernaireViewModel avm;

    //Intent message
    protected static final String DB_COMPLETE_MESSAGE = "database";

    private int qnIndex = 0;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Get the message from the intent (MainActivity)
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DB_MESSAGE);
        username = intent.getStringExtra(MainActivity.USERNAME);

        //Get the display TextView for this activity and display the message
        TextView dbTV = findViewById(R.id.dbQnNoID);
        dbTV.setText(message);

        //Retrieve database question queries
        downloadJSON();

        //Construct ViewModels
        qvm = new QuestionaireViewModel();
        avm = AnswernaireViewModel.getInstance();

        /*Bind UI objects and initialize UIs*/
        qvm.setTime(qnIndex);
        time = qvm.getTime();
        checkTime();

        progBar = findViewById(R.id.dbQnNoBarID);
        progBar.setMax(qvm.getTotalQns());
        progBar.setProgress(qvm.getQuestionNo());

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

        btnNext = findViewById(R.id.btnNextID);
        btnNext.setVisibility(View.INVISIBLE);



        //debug
        qvm.logQuestionSet();
    }

    /*Callback when user select the answer buttons*/
    public void loadQuestions() {
        //Increment index for next qn
        qnIndex++;

        if (qnIndex < qvm.getTotalQns()) {
            //Re-initialize UIs
            try {
                qvm.setTime(qnIndex);
                time = qvm.getTime();
                checkTime();

                qvm.setQuestionNo(qnIndex);
                qvm.setQn(qnIndex);
                qvm.setAnswerA(qnIndex);
                qvm.setAnswerB(qnIndex);
                qvm.setAnswerC(qnIndex);
                qvm.setAnswerD(qnIndex);
                qvm.setTime(qnIndex);
            } catch (Exception e) {
                Log.e("Btn get callback", e.getMessage());
                qnIndex--;
            }
            //Reload UIs
            try {
                progBar.setProgress(qvm.getQuestionNo());
                qnNoTv.setText("Question " + qvm.getQuestionNo() + " of " + qvm.getTotalQns());
                qnTv.setText(String.valueOf(qvm.getQuestion()));
                btnA.setText(String.valueOf(qvm.getAnswerA()));
                btnB.setText(String.valueOf(qvm.getAnswerB()));
                btnC.setText(String.valueOf(qvm.getAnswerC()));
                btnD.setText(String.valueOf(qvm.getAnswerD()));
            } catch (Exception e) {
                Log.e("Btn set callback", e.getMessage());
                qnIndex--;
            }
        } else {
            //Create an intent for the third activity
            Intent intent = new Intent(this, CompleteActivity.class);
            String message = "- Complete -";
            intent.putExtra(DB_COMPLETE_MESSAGE, message);   //key-value pair
            //Start the intended activity
            startActivity(intent);
            finish();
        }

    }

    /*Callback when user select the answer buttons*/
    public void choiceBtnHandler(View view){
        //Selected button will trigger an insert query into db
        Button btnSelected = findViewById(view.getId());        //Get id of selected button
        String ansTxt = btnSelected.getText().toString();       //String formatting...
        String choice = String.valueOf(ansTxt.charAt(0));
        String answer = ansTxt.substring(ansTxt.indexOf(" ") + 1, ansTxt.length() - 1);

        loadResponses(String.valueOf(qvm.getQuestionNo()), choice, answer, username, "");
        Log.i("BtnSelected", "qNo:" + qvm.getQuestionNo() + ",choice:" + choice + ",answer:" + answer);
        enqueueConnection();

        loadQuestions();
    }

    public void nextBtnHandler(View view){
        loadQuestions();
    }

    private boolean checkTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            Date strDate = format.parse(time);
            if(new Date().before(strDate)){
                isValid = true;
                btnA.setClickable(true);
                btnB.setClickable(true);
                btnC.setClickable(true);
                btnD.setClickable(true);
                btnNext.setVisibility(View.INVISIBLE);
                Log.i("Time compare", time + " is valid");
            }else {
                isValid = false;
                btnA.setClickable(false);
                btnB.setClickable(false);
                btnC.setClickable(false);
                btnD.setClickable(false);
                btnNext.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Question has expired, proceed to next question", Toast.LENGTH_LONG);
                Log.i("Time compare", time + " is not valid ");
            }
        } catch (Exception e){
            Log.e("Parse error", e.getMessage());
        }
        return isValid;
    }

    /*Load responses to insert DB*/
    private void loadResponses(String qnNo, String choice, String answer, String username, String comments){
        avm.setQuestionNO(qnNo);
        avm.setChoice(choice);
        avm.setAnswer(answer);
        avm.setUsername(username);
        avm.setComments(comments);
    }

    /*Uploading db responses through web*/
    public void enqueueConnection(){
        //Connection to insert db query
        ConnectionInsert connectionInsert = new ConnectionInsert();
        try {
            connectionInsert.connections();
        }catch (Exception e){
            Log.e("DBactivity enQ", e.getMessage());
        }
    }

    /*Downloading db queries from web*/
    private void downloadJSON(){
        //Connection to retrieve db query
        ConnectionHelper getJSON = new ConnectionHelper();
        getJSON.execute();
    }

}
