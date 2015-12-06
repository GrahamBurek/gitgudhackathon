package com.gitgud.hackathon;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;


public class registerActivity extends AppCompatActivity {

    private EditText usernameField, firstNameField,lastNameField,emailField,passwordField,repeatPasswordField;
    private TextView query_message;

    private class RegisterUserTask extends AsyncTask<String, Void, String> {



        public RegisterUserTask(Context context) {
        }

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... args) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://php-grahamburek.rhcloud.com/index.php?operation=" + "register" + "&username="
                        + args[0]+ "&firstName=" + args[1] + "&lastName=" + args[2] + "&email=" + args[3] + "&password="
                        + args[4] + "&passwordRepeat=" + args[5]);

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
            query_message.setText(result);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        query_message = (TextView) findViewById(R.id.query_message);
        usernameField = (EditText) findViewById(R.id.username);
        firstNameField = (EditText) findViewById(R.id.firstName);
        lastNameField = (EditText) findViewById(R.id.lastName);
        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);
        repeatPasswordField = (EditText) findViewById(R.id.repeatPassword);

    }

    public void register(View view){
        String username = usernameField.getText().toString();
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String passwordRepeat = repeatPasswordField.getText().toString();

        new RegisterUserTask(this).execute(username,firstName,lastName,email,password,passwordRepeat);

    }




    }
