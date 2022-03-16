package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import android.os.AsyncTask;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class BookingActivity extends AppCompatActivity {
    private RecyclerView tRecyclerView;
    private RecyclerView.Adapter tAdapter;
    private RecyclerView.LayoutManager tLayoutManager;
    String usid;
    String center_id;
    Agent agent_curr;
    Intent intent;
    private String date_choice;

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
        System.out.println("Booking Page Here");
        Button button1 = (Button) findViewById(R.id.todaybutton);
        Button button2 = (Button) findViewById(R.id.tomorrowbutton);
        Button button3 = (Button) findViewById(R.id.thirdbutton);
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
        String curr_date = intent.getStringExtra("datechoice");
        System.out.println("Center Id:" + center_id);
        agent_curr = ((MyApplication) this.getApplication()).getAgent();
        usid = agent_curr.getUnique_userid();
        System.out.println("User Id: "+ usid);
        ArrayList<TimeslotItem> mylist = agent_curr.view_all_timeslots(center_id,curr_date);
        System.out.println(mylist.size());
        for(int i = 0; i < mylist.size();i++){
            mylist.get(i).setUser_id(usid);
        }
        System.out.println(mylist.get(0).getCurrent_users());
        System.out.println(mylist.get(0).getStart());
        tAdapter = new BookingAdapter(mylist, agent_curr);
        tRecyclerView.setLayoutManager(tLayoutManager);
        tRecyclerView.setAdapter(tAdapter);
    }

    public void onClickMap(View view) {
        System.out.println("BACK TO MAP PAGE");
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
        System.out.println("Today's Timeslot");
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
        System.out.println("Today's Timeslot");
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
        System.out.println("Today's Timeslot");
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





}