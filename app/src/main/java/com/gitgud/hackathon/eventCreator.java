package com.gitgud.hackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gitgud.hackathon.database.checkLogin;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class eventCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedpreferences = getSharedPreferences(checkLogin.LOGIN_PREFS, Context.MODE_PRIVATE);
        boolean usernameSet = sharedpreferences.contains("username");
        boolean passwordSet = sharedpreferences.contains("password");

        if (usernameSet && passwordSet) {
            String username = sharedpreferences.getString("username", "nothing");
            String password = sharedpreferences.getString("password", "nothing");
            new checkLogin(this).execute(username, password);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        ActionBar actionBar = getSupportActionBar();
        return true;
    }
}
