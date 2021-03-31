package com.example.simplesurvey;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {
    Connection connect;
    String connectionResult;
    Boolean isSuccess = false;

    public List<Map<String, String>>getdata(){
        List<Map<String,String>> data=null;
        data = new ArrayList<Map<String,String>>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connections();
            if (connect == null){
                connectionResult = "Check Your Internet Access";
            }else{
                String query = "select * from questions";
                Statement stmt = connect.createStatement();
                ResultSet rset = stmt.executeQuery(query);

                while(rset.next()){
                    Map<String,String> datanum = new HashMap<String,String>();
                    datanum.put("QnNo", rset.getString("questionNO"));
                    datanum.put("question", rset.getString("question"));
                    datanum.put("a", rset.getString("answerA"));
                    datanum.put("b", rset.getString("answerB"));
                    datanum.put("c", rset.getString("answerC"));
                    datanum.put("d", rset.getString("answerD"));
                    datanum.put("time", rset.getString("time"));
                    datanum.put("Desc", rset.getString("Description"));
                    data.add(datanum);
                }

                connectionResult = "Successful";
                isSuccess = true;
                connect.close();
            }
        }catch (Exception e){
            connectionResult = e.getMessage();
            isSuccess = false;
        }
        return data;
    }
}
