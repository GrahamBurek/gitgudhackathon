package com.gitgud.hackathon;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gitgud.hackathon.database.checkLogin;

import java.util.ArrayList;

public class FeedActivity extends AppCompatActivity {
    ListView feed_view;
    ArrayAdapter feed_adapter;
    ArrayList feed_list = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        feed_view = (ListView) findViewById(R.id.feed_listview);
        feed_list.add("Prof. K: Class Cancelled Today");
        feed_list.add("MCCS: Hackathon moved to 12/4");
        feed_adapter = new ArrayAdapter(this,
                R.layout.row_feed,
                R.id.feed_label,
                feed_list);

        feed_view.setAdapter(feed_adapter);
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

}
