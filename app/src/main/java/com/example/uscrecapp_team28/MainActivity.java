package com.example.uscrecapp_team28;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String android_id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference reference;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        // Agent agent_curr = new Agent(android_id);
        System.out.println("AndroidID" + android_id);
        Agent agent_curr = ((MyApplication) this.getApplication()).getAgent();
        agent_curr.setDevice_id(android_id);
        // check if wifi is connected
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!mWifi.isConnected()) {
            System.out.println("WIFI IS NOT CONNECTED!!!!!!!");
            Intent i = new Intent(MainActivity.this, WifiActivity.class);
            startActivity(i);
            return;
        }
        System.out.println("WIFI CONNECTED SUCCESS");
        // check if the current user device is already logged in, if yes, to Map
        if (agent_curr.check_loggedin()) {
            System.out.println("CALL AGENT INIT");
            agent_curr.init_info();
            System.out.println(agent_curr.getUnique_userid());
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);
        }
        // if no, to login page to login
        else {
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        finish();
    }
}