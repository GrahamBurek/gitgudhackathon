package com.gitgud.hackathon;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gitgud.hackathon.database.checkLogin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText usernameField;
    EditText passwordField;
    TextView query_message;
    private String username;
    private String password;

    public LoginActivity(){

    }


    private class LoginUserTask extends AsyncTask<String, Void, String> {



        public LoginUserTask(Context context) {
        }

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... args) {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://php-grahamburek.rhcloud.com/index.php?operation=" + "login" + "&username="
                        + args[0]+ "&password=" + args[1]);

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
            String[] strings = result.split(" ");
            SharedPreferences sharedPreferences = getSharedPreferences(checkLogin.LOGIN_PREFS,MODE_PRIVATE);
            sharedPreferences.edit().putString("username", username).commit();
            sharedPreferences.edit().putString("password", password).commit();
            query_message.setText(strings[0] + " " + strings[1]);

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameField = (EditText) findViewById(R.id.login_username);
        passwordField = (EditText) findViewById(R.id.login_password);
        query_message = (TextView) findViewById(R.id.login_query_message);

    }

    public void login(View v){
         username = usernameField.getText().toString();
         password = passwordField.getText().toString();

        new LoginUserTask(this).execute(username,password);
    }

}