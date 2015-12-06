package com.gitgud.hackathon;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    JSONObject mainObject;
    ArrayList<String> eventList = new ArrayList<String>();
    public final static String EVENT_TITLE = "com.gitgud.hackathon.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainListView = (ListView) findViewById(R.id.main_listview);
        getEvents();


        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                eventList);

        mainListView.setAdapter(mArrayAdapter);
        mainListView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            case R.id.action_new_event:
                Intent intent = new Intent(this, eventCreator.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewEvent(View view){
        Intent intent = new Intent(this, DisplayEventActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, DisplayEventActivity.class);
        String title = (String) parent.getItemAtPosition(position);
        intent.putExtra(EVENT_TITLE, title);
        startActivity(intent);

        // Log the item's position and contents
        // to the console in Debug
        Log.d("omg android", position + ": " + eventList.get(position));
    }

    private class GetEventsTask extends AsyncTask<String, Void, String> {

        private Context context;

        public GetEventsTask(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... args) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://php-grahamburek.rhcloud.com/get_events.php");

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
            if(result != null){


                JSONArray array = null;
                try {
                    array = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < array.length(); i++) {
                    int id = 0;
                    String name = "";
                    String time = "";
                    JSONObject row = null;
                    try {
                        row = array.getJSONObject(i);
                        id = row.getInt("event_id");
                        name = row.getString("event_name");
                        time = row.getString("time");

                        eventList.add(name + " " + time);
                        mArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

//            String[] strings = result.split("VRIRV"); // Split result at spaces
//
//                for(Integer i=0; i<21; i++){
//                    // Every sixth element is an id
//                    if((i+7)%7==0){
//                        ArrayList<String> fields = new ArrayList<>();
//                        for(int j=1;j<i+7;j++){
//                            fields.add(strings[j]);
//                        }
//                        result_hash.put(strings[i],fields);
//                        eventList.add(result_hash.get(strings[i]).get(1) + result_hash.get("1").get(1));
//                        mArrayAdapter.notifyDataSetChanged();
//                    }
//                }
            }
        }

    }

    public void getEvents(){
        new GetEventsTask(this).execute();
    }
}
