package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BookingInformationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView mHistoryRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mHistoryAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mHistoryLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_information);
        mRecyclerView = findViewById(R.id.recyclerView);
        mHistoryRecyclerView = findViewById(R.id.HistoryrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mHistoryRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mHistoryLayoutManager = new LinearLayoutManager(this);
        //call agent to display reservation
        Agent agent_curr = ((MyApplication) this.getApplication()).getAgent();

        HashMap<String,ArrayList<BookingItem>> m = agent_curr.view_all_reservations();
        ArrayList<BookingItem> futureList = (ArrayList<BookingItem>) m.get("future");
        ArrayList<BookingItem> historyList = (ArrayList<BookingItem>) m.get("history");
        //set up adapter
        mAdapter = new BookingInformationAdapter(futureList,agent_curr);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mHistoryAdapter = new PastBookingInformationAdapter(historyList);
        mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // this method relocate the two buttons (two gyms) relative to different screens
        super.onWindowFocusChanged(hasFocus);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        // small window
        View window = findViewById(R.id.recyclerView);
        View historyWindow = findViewById(R.id.HistoryrecyclerView);
        LinearLayout.LayoutParams window_param = (LinearLayout.LayoutParams) window.getLayoutParams();
        LinearLayout.LayoutParams historyWindow_param = (LinearLayout.LayoutParams) historyWindow.getLayoutParams();
        window_param.height = height/ 3;
        historyWindow_param.height= height/ 3;
        window.setLayoutParams(window_param);
        historyWindow.setLayoutParams(historyWindow_param);
    }
    public void onClickReturn(View view){
        startActivity(new Intent(this,MapActivity.class));
    }

}