package com.gitgud.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DisplayEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra(MainActivity.EVENT_TITLE));
        setContentView(R.layout.activity_display_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void viewEvent(View view){
        Intent intent = new Intent(this, userProfile.class);
        startActivity(intent);
    }

}
