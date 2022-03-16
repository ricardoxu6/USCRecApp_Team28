package com.example.uscrecapp_team28;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.sql.*;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String android_id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannels();
        setContentView(R.layout.activity_main);
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        // Agent agent_curr = new Agent(android_id);
        System.out.println("AndroidID" + android_id);
        Agent agent_curr = ((MyApplication) this.getApplication()).getAgent();
        agent_curr.setDevice_id(android_id);
        // check if wifi is connected
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //listen for databse change
        reference = db.collection("User").document("User");
        reference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null && value.exists()) {
                    System.out.println("current user id" + agent_curr.getUnique_userid());
                    System.out.println( "Current data: " + value.getData());
                    for (Map.Entry<String, Object> entry : value.getData().entrySet()){
                        //compare with current input
                        if(entry.getValue().equals(agent_curr.getUnique_userid())){
                            //send notification
                            System.out.println("send notification");
                            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(),"channel_1").setSmallIcon(R.drawable.phoneicon).setContentTitle("USCRecAPP").setContentText("ther is a spot available!").setPriority(NotificationCompat.PRIORITY_DEFAULT);
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                            notificationManagerCompat.notify(1,mBuilder.build());
                        }
                    }
                }
            }
        });
        if (!mWifi.isConnected()) {
            System.out.println("WIFI IS NOT CONNECTED!!!!!!!");
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
    //create notification channel
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    "channel_1",
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
}