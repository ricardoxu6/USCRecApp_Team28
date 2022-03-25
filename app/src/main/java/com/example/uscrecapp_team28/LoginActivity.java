package com.example.uscrecapp_team28;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.Secure;
import java.sql.*;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;

public class LoginActivity extends AppCompatActivity {

    private Agent agent_curr;
    Intent mServiceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //TODO add this code to all pages oncreate
        mServiceIntent = new Intent(this, NotificationService.class);
        mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
        if (!isMyServiceRunning(NotificationService.class)) {
            ContextCompat.startForegroundService(this,mServiceIntent);
        }
        //TODO end
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
            usernameView.getText().clear();
            passwordView.getText().clear();
            System.out.println("WRONG USERNAME / PASSWORD");
        }
    }

    public void onLoginClick(View view) {
        onLoginHelper();
    }

    //TODO add the following code the all pages
    @Override
    protected void onDestroy() {
        System.out.println("ondestroy in service");
        CustomBroadcastReceiver.setBroadcastReceiverId(agent_curr.getUnique_center_id());
        Intent broadcastIntent = new Intent(this, CustomBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
        System.out.println("destroy the mainactivity service");
        super.onDestroy();

    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    //TODO endg
}