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

public class LoginActivity extends AppCompatActivity {

    private Agent agent_curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Agent agent_curr = ((MyApplication) this.getApplication()).getAgent();
    }

    public void onLoginClick(View view) {
        EditText usernameView = (EditText)findViewById(R.id.username);
        EditText passwordView = (EditText)findViewById(R.id.password);
        String usernameStr = usernameView.getText().toString();
        String passwordStr = passwordView.getText().toString();
        agent_curr = ((MyApplication) this.getApplication()).getAgent();
        agent_curr.setUsername(usernameStr);
        agent_curr.setPassword(passwordStr);
        System.out.println(usernameStr);
        System.out.println(passwordStr);
        if (agent_curr.user_login()) {
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


}