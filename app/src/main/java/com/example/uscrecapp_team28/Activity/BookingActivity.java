package com.example.uscrecapp_team28.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uscrecapp_team28.Class.Agent;
import com.example.uscrecapp_team28.Helper.BookingAdapter;
import com.example.uscrecapp_team28.Helper.CustomBroadcastReceiver;
import com.example.uscrecapp_team28.MyApplication;
import com.example.uscrecapp_team28.Helper.NotificationService;
import com.example.uscrecapp_team28.R;
import com.example.uscrecapp_team28.Class.TimeslotItem;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView tRecyclerView;
    Handler mHandler;

    public RecyclerView.Adapter gettAdapter() {
        return tAdapter;
    }

    private RecyclerView.Adapter tAdapter;
    private RecyclerView.LayoutManager tLayoutManager;
    String usid;
    String center_id;
    Agent agent_curr;
    Intent intent;
    private String date_choice;
    Intent mServiceIntent;

    public String getDate_choice() {
        return date_choice;
    }

    public void setDate_choice(String date_choice) {
        this.date_choice = date_choice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        mServiceIntent = new Intent(this, NotificationService.class);
        mServiceIntent.putExtra("userId",agent_curr.getUnique_userid());
        if (!isMyServiceRunning(NotificationService.class)) {
            ContextCompat.startForegroundService(this,mServiceIntent);
        }
        this.mHandler = new Handler();
//        m_Runnable.run();
        // System.out.println("Booking Page Here");
        Button button1 = (Button) findViewById(R.id.todaybutton);
        Button button2 = (Button) findViewById(R.id.tomorrowbutton);
        Button button3 = (Button) findViewById(R.id.thirdbutton);
        TextView center_name = (TextView) findViewById(R.id.center_name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String today = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1);
        String third =  sdf.format(cal.getTime());
        button1.setText(today);
        button2.setText(tomorrow);
        button3.setText(third);
        tRecyclerView = findViewById(R.id.TrecyclerView);
        tRecyclerView.setHasFixedSize(true);
        tLayoutManager = new LinearLayoutManager(this);
        intent = getIntent();
        center_id = intent.getStringExtra("gym");
        if (center_id.equals("1")) {
            center_name.setText("@ Lyon Recreation Center");
        } else {
            center_name.setText("@ USC Village Fitness Center");
        }
        String curr_date = intent.getStringExtra("datechoice");
        usid = agent_curr.getUnique_userid();
        // System.out.println("User Id: "+ usid);
        ArrayList<TimeslotItem> mylist = agent_curr.view_all_timeslots(center_id, curr_date);
        // System.out.println(mylist.size());
        for(int i = 0; i < mylist.size();i++){
            mylist.get(i).setUser_id(usid);
        }
        // System.out.println(mylist.get(0).getCurrent_users());
        // System.out.println(mylist.get(0).getStart());
        try {
            tAdapter = new BookingAdapter(mylist, agent_curr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tRecyclerView.setLayoutManager(tLayoutManager);
        tRecyclerView.setAdapter(tAdapter);
    }

//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//        {
//            Toast.makeText(BookingActivity.this,"refreshing",Toast.LENGTH_SHORT).show();
//            BookingActivity.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//    };

    public void onClickRefresh(View view) {
        // System.out.println("REFRESH");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    public void onClickMap(View view) {
        // System.out.println("BACK TO MAP PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(BookingActivity.this, MapActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickToday(View view) {
        // System.out.println("Today's Timeslot");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Context context = getApplicationContext();
        Intent i = new Intent(context, BookingActivity.class);
        i.putExtra("gym", center_id);
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        i.putExtra("datechoice", strDate);
        startActivity(i);
        finish();
    }

    public void onClickSecond(View view) {
        // System.out.println("Today's Timeslot");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Context context = getApplicationContext();
        Intent i = new Intent(context, BookingActivity.class);
        i.putExtra("gym", center_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        String tomorrow =  sdf.format(cal.getTime());
        i.putExtra("datechoice", tomorrow);
        startActivity(i);
        finish();

    }

    public void onClickThird(View view) {
        // System.out.println("Today's Timeslot");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(BookingActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Context context = getApplicationContext();
        Intent i = new Intent(context, BookingActivity.class);
        i.putExtra("gym", center_id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        String third =  sdf.format(cal.getTime());
        i.putExtra("datechoice", third);
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