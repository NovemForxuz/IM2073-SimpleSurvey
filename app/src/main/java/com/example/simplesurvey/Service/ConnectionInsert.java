/*Communication with Remote Data Source*/ 

package com.example.simplesurvey.Service;

import android.util.Log;

import com.example.simplesurvey.Model.AnswernaireViewModel;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**-----Communications-----*/
/*ConnectionInsert ---> AnswernaireVM
 */

public class ConnectionInsert {
    //Declare connection variables
    private static final String BASE_URL = "http://10.27.23.30/api/insert_response.php";
    private OkHttpClient client;
    private RequestBody body;

    //Declare communications
    private AnswernaireViewModel ansVM;

    public ConnectionInsert(){
        client = new OkHttpClient();
        ansVM = AnswernaireViewModel.getInstance();
    }

    //Initiate insert query to db through web
    public void connections(){
        try{
            body = new FormEncodingBuilder()
                    .add("questionNO", ansVM.getQuestionNO())
                    .add("choice", ansVM.getChoice())
                    .add("answer", ansVM.getAnswer())
                    .add("username", ansVM.getUsername())
                    .add("comments", ansVM.getComments())
                    .build();
        }catch (Exception e){
            Log.e("Body", e.getMessage());
            Log.e("Body contents", "qNo:"+ansVM.getQuestionNO()+",choice:"+ansVM.getChoice());
        }
        Request request = new Request.Builder().url(BASE_URL).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Response callback", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try{
                    String resp = response.body().string();
                    Log.i("Connection Response", resp);
                }catch (IOException e){
                    Log.e("Connection Response", e.getMessage());
                }
            }
        });
    }
}
