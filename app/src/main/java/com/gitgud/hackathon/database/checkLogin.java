package com.gitgud.hackathon.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.gitgud.hackathon.LoginActivity;
import com.gitgud.hackathon.MainActivity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Graham on 12/5/2015.
 */
public class checkLogin extends AsyncTask<String,Void,String> {

    public static final String LOGIN_PREFS = "loginPreferences";

    Activity activity;

    public checkLogin(Activity activity){
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... args) {
        String username = args[0];
        String password = args[1];

        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://php-grahamburek.rhcloud.com/index.php?operation=" + "login" + "&username="
                    + username + "&password=" + password);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            StringBuilder builder = new StringBuilder();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                builder.append(current);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace(); //If you want further info on failure...
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){
        if(result.substring(0,16).equals("Login successful")){


    } else {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
        }
    }
}
