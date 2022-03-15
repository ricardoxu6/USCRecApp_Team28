package com.example.uscrecapp_team28;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import java.sql.*;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;

public class LoginActivity extends AppCompatActivity {

    private Agent agent_curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        EditText editText = (EditText) findViewById(R.id.password);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onLoginHelper();
                    handled = true;
                }
                return handled;
            }
        });
    }

//    public boolean onEditorAction(TextView exampleView, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER
//                && event.getAction() == KeyEvent.ACTION_DOWN) {
//            onLoginHelper();  //match this behavior to your 'Send' (or Confirm) button
//        }
//        return true;
//    }

    public void onLoginHelper() {
        EditText usernameView = (EditText)findViewById(R.id.username);
        EditText passwordView = (EditText)findViewById(R.id.password);
        String usernameStr = usernameView.getText().toString();
        String passwordStr = passwordView.getText().toString();
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        this.agent_curr.setUsername(usernameStr);
        this.agent_curr.setPassword(passwordStr);
//        System.out.println(usernameStr);
//        System.out.println(passwordStr);
        if (this.agent_curr.user_login()) {
            System.out.println("LOGIN SUCCESS");
            Intent i = new Intent(LoginActivity.this, MapActivity.class);
            startActivity(i);
            finish();
        }
        else {
            findViewById(R.id.wrong).setVisibility(View.VISIBLE);
            System.out.println("WRONG USERNAME / PASSWORD");
        }
    }

    public void onLoginClick(View view) {
        onLoginHelper();
    }
}