package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

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
        ArrayList<BookingItem> exampleList = new ArrayList<>();
        exampleList.add(new BookingItem( "lyon", "3.12"));
        exampleList.add(new BookingItem("lyon", "2.12"));
        ArrayList<BookingItem> historyList = new ArrayList<>();
        historyList.add(new BookingItem("lyon","2.23"));
        historyList.add(new BookingItem("lyon","12.2"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mHistoryRecyclerView = findViewById(R.id.HistoryrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mHistoryRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mHistoryLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BookingInformationAdapter(exampleList);
        mHistoryAdapter = new BookingInformationAdapter(historyList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);
    }
}