package com.example.uscrecapp_team28.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

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
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        mServiceIntent = new Intent(this, NotificationService.class);
        mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
        mServiceIntent.putExtra("command",false);
        if (!isMyServiceRunning(NotificationService.class) && agent_curr.getNotification_on()) {
            System.out.println("setting activity: start the notification service");
            ContextCompat.startForegroundService(this,mServiceIntent);
        }
        TextView message = (TextView) findViewById(R.id.notification_show);
        SwitchCompat switchMain = (SwitchCompat)findViewById(R.id.switchMAIN);
        if (agent_curr.getNotification_on()) {
            switchMain.setChecked(true);
            switchMain.setText("Notification ON ");
            message.setText("Currently, you will receive notification " + agent_curr.getNotification_time() + " minutes before your next reservation");
        } else {
            switchMain.setChecked(false);
            switchMain.setText("Notification OFF");
            message.setText("Currently, you will NOT receive notification for upcoming reservations");
        }

        switchMain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    agent_curr.setNotification_on(true);
                    TextView message = (TextView) findViewById(R.id.notification_show);
                    message.setText("Currently, you will receive notification " + agent_curr.getNotification_time() + " minutes before your next reservation");
                    switchMain.setText("Notification ON ");  //To change the text near to switch
                    System.out.println("Notification ON ");
                    mServiceIntent = new Intent(getApplicationContext(), NotificationService.class);
                    mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
                    mServiceIntent.putExtra("command",false);
                    if (!isMyServiceRunning(NotificationService.class)) {
                        System.out.println("setting: start the notification service");
                        ContextCompat.startForegroundService(getApplicationContext(),mServiceIntent);
                    }
                }
                else {
                    agent_curr.setNotification_on(false);
                    TextView message = (TextView) findViewById(R.id.notification_show);
                    message.setText("Currently, you will NOT receive notification for upcoming reservations");
                    switchMain.setText("Notification OFF");  //To change the text near to switch
                    System.out.println("Notification OFF");
                    //stop the service
//                    agent_curr.logout();
//                    agent_curr.setUnique_userid("");
                    mServiceIntent = new Intent(getApplicationContext(), NotificationService.class);
                    mServiceIntent.putExtra("userId","");
                    mServiceIntent.putExtra("command",true);
                    startService(mServiceIntent);
                    stopService(mServiceIntent);
                    System.out.println("setting: stop the foreground service");
                    Intent i = new Intent(SettingActivity.this, SettingActivity.class);
                    startActivity(i);
                    finish();
                    return;
//                    overridePendingTransition(0, 0);
//
//                    overridePendingTransition(0, 0);
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

    public void onClickConfirmChange(View view) {
        // System.out.println("BACK TO MAP PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        EditText minuteView = (EditText)findViewById(R.id.textremindedit);
        try
        {
            Integer new_minute = Integer.parseInt(minuteView.getText().toString());
            TextView message = (TextView) findViewById(R.id.notification_show);
            message.setText("Currently, you will receive notification " + new_minute + " minutes before your next reservation");
            System.out.println("SUCCESS");
            agent_curr.setNotification_time(new_minute);
            System.out.println(agent_curr.getNotification_time());
        }
        catch (Exception e)
        {
            // handle the exception
            findViewById(R.id.wrongwrong).setVisibility(View.VISIBLE);
            minuteView.getText().clear();
            System.out.println("FAIL");
        }

    }

    @Override
    protected void onDestroy() {
        // System.out.println("ondestroy in service");
        //create if on
        if (!isMyServiceRunning(NotificationService.class) && agent_curr.getNotification_on()) {
            CustomBroadcastReceiver.setBroadcastReceiverId(agent_curr.getUnique_userid());
            Intent broadcastIntent = new Intent(this, CustomBroadcastReceiver.class);
            sendBroadcast(broadcastIntent);
             System.out.println("send broadcast");
        }

        super.onDestroy();
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                System.out.println("setting activity: find already running service");
                return true;
            }
        }
        return false;
    }
}