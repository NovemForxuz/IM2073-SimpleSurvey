package com.example.simplesurvey;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DatabaseActivity extends AppCompatActivity {

    //Declare UIs
    ListView listView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        //Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.DB_MESSAGE);

        //Get the display TextView for this activity and display the message
        TextView dbTV = findViewById(R.id.dbTextViewID);
        dbTV.setText(message);

        listView = findViewById(R.id.lvTableID);
        downloadJSON("http://10.0.2.2/api/query_questions.php");
    }

    /**Downloading db content form web*/
    private void downloadJSON(final String urlWebService){
        class DownloadJSON extends AsyncTask<Void,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine()) != null){
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                }catch(Exception e){
                    Log.e("Error from Connection", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null)
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                else
                    Log.e("PostExecute", "- empty -");
                try {
                    loadIntoListView(s);
                    Log.e("PostExecute", "view loaded.");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        String[] questionaire = new String[jsonArray.length()];
        for (int i=0; i< jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            questionaire[i] = obj.getString("questionNO") + ". "
                    + obj.getString("question")
                    + "\nA. " + obj.getString("answerA")
                    + "\nB. " + obj.getString("answerB")
                    + "\nC. " + obj.getString("answerC")
                    + "\nD. " + obj.getString("answerD")
                    + "\nTime: " + obj.getString("time")
                    + "\n" + obj.getString("Description");
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questionaire);
        listView.setAdapter(arrayAdapter);
    }

    /**Callback when user selects the "BACK" button*/
    public void goBackHandler(View view){
        //Close the activity
        finish();
    }

}
