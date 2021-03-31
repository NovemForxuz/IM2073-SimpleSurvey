package com.example.simplesurvey;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String IP, DB, DBUserName, DBPassword;
    @SuppressLint("NewApi")
    public Connection connections(){
        IP = "10.0.2.2";
        DB = "android";
        DBUserName = "myuser";
        DBPassword = "password123";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionURL = "jdbc:mysql://" + IP + ";databaseName=" + DB + ";user=" + DBUserName + ";password=" + DBPassword;
            connection = DriverManager.getConnection(connectionURL);
        }catch (SQLException ex){
            Log.e("Error from SQL", ex.getMessage());
        }catch (ClassNotFoundException e){
            Log.e("Error from Class", e.getMessage());
        }catch (Exception e){
            Log.e("Error from Exception", e.getMessage());
        }

        return connection;
    }
}
