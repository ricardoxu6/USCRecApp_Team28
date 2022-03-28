package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private Agent agent_curr;
    String photourl;
    Intent mServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //TODO add this code to all pages oncreate
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        mServiceIntent = new Intent(this, NotificationService.class);
        mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
        if (!isMyServiceRunning(NotificationService.class)) {
            ContextCompat.startForegroundService(this,mServiceIntent);
        }
        //TODO end
        ArrayList<String> profile_result = agent_curr.view_profile();
//        String unique_userid = profile_result.get(0);

        String uscid = profile_result.get(1);
        final TextView profile_uscid = (TextView) findViewById(R.id.profile_uscid);
        profile_uscid.setText("USCid: " + uscid);

        String username = profile_result.get(2);
        final TextView profile_username = (TextView) findViewById(R.id.profile_username);
        profile_username.setText("Username: " + username);

        // photourl = "https://media-exp1.licdn.com/dms/image/C5603AQFxpC5CYYULNA/profile-displayphoto-shrink_800_800/0/1624353277071?e=1652918400&v=beta&t=84ukoO8jlXa6Di4JE2VbtMd-klLCCcQK6aoQT1G5GwY";
        photourl = profile_result.get(4);
        try {
            new AddPhotoTask().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = profile_result.get(5);
        final TextView profile_name = (TextView) findViewById(R.id.profile_name);
        profile_name.setText(name.toUpperCase());

        String email = profile_result.get(6);
        final TextView profile_email = (TextView) findViewById(R.id.profile_email);
        profile_email.setText("Email: " + email);

        String device_id = profile_result.get(7);
        final TextView profile_dvid = (TextView) findViewById(R.id.profile_deviceid);
        profile_dvid.setText("DeviceID: " + device_id);

        // call agent to display booking history statistics
        Agent agent_curr = ((MyApplication) this.getApplication()).getAgent();
        HashMap<String,ArrayList<BookingItem>> m = agent_curr.view_all_reservations();
        ArrayList<BookingItem> futureList = (ArrayList<BookingItem>) m.get("future");
        ArrayList<BookingItem> historyList = (ArrayList<BookingItem>) m.get("history");
        int upcoming = futureList.size();
        int history = historyList.size();
        int total = upcoming + history;
        final TextView profile_total = (TextView) findViewById(R.id.profile_total);
        profile_total.setText(String.valueOf(total));
        final TextView profile_upcoming = (TextView) findViewById(R.id.profile_upcoming);
        profile_upcoming.setText(String.valueOf(upcoming));
        final TextView profile_history = (TextView) findViewById(R.id.profile_history);
        profile_history.setText(String.valueOf(history));
    }

    public void onClickBack(View view) {
        // System.out.println("BACK TO MAP PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(ProfileActivity.this, MapActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickLogout(View view) {
        // System.out.println("LOGOUT");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        this.agent_curr.logout();
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
    }

    class AddPhotoTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ImageView i = (ImageView)findViewById(R.id.profile_photo);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(photourl).getContent());
                i.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //TODO add the following code the all pages
    @Override
    protected void onDestroy() {
//        System.out.println("ondestroy in service");
        if (!isMyServiceRunning(NotificationService.class)) {
            CustomBroadcastReceiver.setBroadcastReceiverId(agent_curr.getUnique_userid());
            Intent broadcastIntent = new Intent(this, CustomBroadcastReceiver.class);
            sendBroadcast(broadcastIntent);
        }
//        System.out.println("destroy the mainactivity service");
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
    //TODO end
}