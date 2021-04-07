/*Communication with Remote Data Source*/

package com.example.simplesurvey;

import android.os.AsyncTask;
import android.util.Log;

import com.example.simplesurvey.Model.QnRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**-----Communications-----*/
/*ConnectionHelper <--- QnRepository
 */

public class ConnectionHelper extends AsyncTask<Void, Void, String> {

    private String urlWebService;
    private QnRepository dbViewModel;

    public ConnectionHelper (){
        //Initialize url
        urlWebService = "http://10.27.23.30/api/query_questions.php";
        /* Used php to query from MySQL via xampp server,
        *  Change IP address of url to server host's IP address
        */

        dbViewModel = QnRepository.getInstance();
    }

    //Retrieve questions queries from database
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
        }catch(Exception e) {
            Log.e("Error from Connection", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null)
            Log.i("DB result (JSON)",s);
        else
            Log.e("PostExecute", "- empty -");

        //Storing data into Repository
        try {
            dbViewModel.setQuestionaire(s);
            Log.i("PostExecute", "listView loaded.");
            Log.i("PostExecute", s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
