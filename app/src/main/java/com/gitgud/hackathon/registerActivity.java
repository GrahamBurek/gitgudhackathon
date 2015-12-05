package com.gitgud.hackathon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gitgud.hackathon.database.Database;
import com.gitgud.hackathon.database.MySQLHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class registerActivity extends AppCompatActivity {

    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private HashMap<String, String> registerForm;
    private Button submitButton;

    private class registerUser extends AsyncTask<HashMap<String,String>, Void, Boolean> {

        @Override
        protected Boolean doInBackground(HashMap<String,String>... form) {


            try {
                Boolean result = Database.register_user(MySQLHelper.getConnection(), form[0]);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            return result;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        submitButton = (Button) findViewById(R.id.register_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForm = new HashMap<String, String>();
                username = (EditText) findViewById(R.id.username);
                firstName =  (EditText)findViewById(R.id.firstName);
                lastName = (EditText) findViewById(R.id.lastName);
                email = (EditText) findViewById(R.id.email);
                password = (EditText) findViewById(R.id.password);
                repeatPassword = (EditText) findViewById(R.id.repeatPassword);
                registerForm.put("firstName", firstName.getText().toString());
                registerForm.put("lastName", lastName.getText().toString());
                registerForm.put("email", email.getText().toString());
                registerForm.put("password", password.getText().toString());
                registerForm.put("repeatPassword", repeatPassword.getText().toString());
                registerForm.put("username", username.getText().toString());
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
