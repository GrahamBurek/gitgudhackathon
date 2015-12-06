package com.gitgud.hackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitgud.hackathon.database.checkLogin;
import com.gitgud.hackathon.database.formatTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayEventActivity extends AppCompatActivity {

    Integer eventId;
    TextView event_title;
    TextView event_time;
    Button user_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        eventId = intent.getIntExtra("id", 0);

        setContentView(R.layout.activity_display_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        event_title = (TextView) findViewById(R.id.event_title);
        event_time = (TextView) findViewById(R.id.event_time);
        user_button = (Button) findViewById(R.id.user_button);
        showEvent();


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
                intent.putExtra("id", eventId);
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
        SharedPreferences sp = getSharedPreferences(checkLogin.LOGIN_PREFS, MODE_PRIVATE);
        String username = sp.getString("username", "nothing");
        new UpdateFollowStatusTask(this).execute(username);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class UpdateFollowStatusTask extends AsyncTask<String,Void,String>{

        Context context;

        public UpdateFollowStatusTask(Context context){
            this.context = context;
        }

        @Override
        protected String doInBackground(String... args) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://php-grahamburek.rhcloud.com/index.php?operation=changeFollowStatus&username=" + args[0] + "&eventID=" + eventId);

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

        }

    }






    private class ShowEventTask extends AsyncTask<Integer, Void, String> {

        private Context context;

        public ShowEventTask(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Integer... args) {
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL("http://php-grahamburek.rhcloud.com/show_event.php?id=" + eventId);

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
                    String first_name = "";
                    JSONObject row = null;
                    try {
                        row = array.getJSONObject(i);
                        id = row.getInt("event_id");
                        name = row.getString("event_name");
                        time = row.getString("time");
                        first_name = row.getString("first_name");
                        Log.d("test: ", name);
                        setTitle(name);
                        user_button.setText(first_name);
                        event_title.setText(name);
                        event_time.setText(formatTime.convertFromMilitary(time));

//                        eventList.add(name + " " + time);
//                        idList.add(id);
//                        mArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    public void showEvent(){
        new ShowEventTask(this).execute(eventId);
    }


}
