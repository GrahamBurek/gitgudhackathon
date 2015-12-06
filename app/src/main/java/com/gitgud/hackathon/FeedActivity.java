package com.gitgud.hackathon;

import android.app.ListActivity;
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

    }

}
