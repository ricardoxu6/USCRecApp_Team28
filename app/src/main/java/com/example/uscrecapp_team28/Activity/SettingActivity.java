package com.example.uscrecapp_team28.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.uscrecapp_team28.Class.Agent;
import com.example.uscrecapp_team28.Helper.CustomBroadcastReceiver;
import com.example.uscrecapp_team28.Helper.NotificationService;
import com.example.uscrecapp_team28.MyApplication;
import com.example.uscrecapp_team28.R;

public class SettingActivity extends AppCompatActivity {

    private Agent agent_curr;
    Intent mServiceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //TODO add this code to all pages oncreate
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        mServiceIntent = new Intent(this, NotificationService.class);
        mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
        if (!isMyServiceRunning(NotificationService.class)) {
            ContextCompat.startForegroundService(this,mServiceIntent);
        }
        //TODO end
        boolean notification = true;
        Switch list_toggle=(Switch)findViewById(R.id.switchAB);
        if (notification) {
            list_toggle.setChecked(true);
            list_toggle.setText("Notification ON ");
        } else {
            list_toggle.setChecked(false);
            list_toggle.setText("Notification OFF");
        }



        list_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    list_toggle.setText("Notification ON ");  //To change the text near to switch
                    System.out.println("Notification ON ");
                }
                else {
                    list_toggle.setText("Notification OFF");  //To change the text near to switch
                    System.out.println("Notification OFF");
                }
            }
        });

//
//        yourSwitchButton.setOnCheckedChangeListener((compoundButton, b) -> {
//            if (b){
//                //open job.
//            }
//            else  {
//                //close job.
//            }
//        });
    }

    public void onClickSettingBack(View view) {
        // System.out.println("BACK TO MAP PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(SettingActivity.this, MapActivity.class);
        startActivity(i);
        finish();
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