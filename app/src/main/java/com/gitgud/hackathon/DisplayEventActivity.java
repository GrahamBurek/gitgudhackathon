package com.gitgud.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DisplayEventActivity extends AppCompatActivity {
    public final static String EVENT_TITLE = "com.gitgud.hackathon.MESSAGE";
    Intent titleIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent titleIntent = getIntent();
        setTitle(titleIntent.getStringExtra(DisplayEventActivity.EVENT_TITLE));
        setContentView(R.layout.activity_display_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        TextView event_title = (TextView) findViewById(R.id.event_title);
        event_title.setText(titleIntent.getStringExtra(DisplayEventActivity.EVENT_TITLE));

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, eventSettings.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void viewUser(View view){
        Intent intent = new Intent(this, userProfile.class);
        startActivity(intent);
    }
    public void updateFollowStatus(View view){
        Intent intent = new Intent(this, DisplayEventActivity.class);
        startActivity(intent);
    }

}
