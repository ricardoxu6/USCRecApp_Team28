package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MapActivity extends AppCompatActivity {

    private Agent agent_curr;
    private RecyclerView mRecyclerView;
    private RecyclerView mHistoryRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mHistoryAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mHistoryLayoutManager;
    private SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        agent_curr.init_info();
        setContentView(R.layout.activity_map);
        mRecyclerView = findViewById(R.id.recyclerMAP);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        //call agent to display reservation
        HashMap<String, ArrayList<BookingItem>> m = agent_curr.view_all_reservations();
        ArrayList<BookingItem> futureList = (ArrayList<BookingItem>) m.get("future");
//        int size_futureList = futureList.size();
//        ArrayList<BookingItem> nearFutureList = new ArrayList<BookingItem>();
//        for (int i=0; i<3; i++) {
//            if (size_futureList > 0) {
//                nearFutureList.add(futureList.get(i));
//                size_futureList -= 1;
//            }
//        }
        //set up adapter
        mAdapter = new RecyclerMapAdapter(futureList,agent_curr);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // this method relocate the two buttons (two gyms) relative to different screens
        super.onWindowFocusChanged(hasFocus);
        ImageView imgv = (ImageView) findViewById(R.id.uscmap);
        System.out.println(imgv.getWidth());
        System.out.println(imgv.getHeight());
        // lyon
        Button bt1 = (Button) findViewById(R.id.button1);
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) bt1.getLayoutParams();
        layoutParams1.leftMargin = imgv.getWidth() * 350 / 756 - layoutParams1.height;
        layoutParams1.topMargin = imgv.getHeight() * 337 / 1012 - layoutParams1.height;
        bt1.setLayoutParams(layoutParams1);
        // village
        Button bt2 = (Button) findViewById(R.id.button2);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) bt2.getLayoutParams();
        layoutParams2.leftMargin = imgv.getWidth() * 553 / 756 - layoutParams2.height;
        layoutParams2.topMargin = imgv.getHeight() * 287 / 1012 - layoutParams2.height;
        bt2.setLayoutParams(layoutParams2);
        // small window
        View window = findViewById(R.id.recyclerMAP);
        LinearLayout.LayoutParams window_param = (LinearLayout.LayoutParams) window.getLayoutParams();
        window_param.height = imgv.getWidth() / 2;
        window.setLayoutParams(window_param);
    }

    public void onClickProfile(View view) {
        System.out.println("TO PROFILE PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickLyon(View view) {
        System.out.println("TO LYON");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingActivity.class);
        i.putExtra("gym", 1);
        startActivity(i);
        finish();
    }

    public void onClickVill(View view) {
        System.out.println("TO VILLAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingActivity.class);
        i.putExtra("gym", 2);
        startActivity(i);
        finish();
    }

    public void onClickSummary(View view) {
        System.out.println("TO SUMMARY");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingInformationActivity.class);
        startActivity(i);
        finish();
    }
}