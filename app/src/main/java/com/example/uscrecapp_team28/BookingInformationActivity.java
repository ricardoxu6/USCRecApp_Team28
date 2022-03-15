package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BookingInformationActivity extends AppCompatActivity {
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
        sp1=this.getSharedPreferences("Login", MODE_PRIVATE);
        setContentView(R.layout.activity_booking_information);
        mRecyclerView = findViewById(R.id.recyclerView);
        mHistoryRecyclerView = findViewById(R.id.HistoryrecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mHistoryRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mHistoryLayoutManager = new LinearLayoutManager(this);
        new GetBookingInformation().execute();

    }

    class GetBookingInformation extends AsyncTask<Void, Void, Void> {
        ArrayList<BookingItem> futureList = new ArrayList<>();
        ArrayList<BookingItem> historyList = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids){
            try{
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
//                String userId = sp1.getString("user_id",null);
                String userId = "0";
                //query the database for all user's reservation
                String query = String.format("SELECT \n" +
                        "\treservation.reservation_id,datelist.date,center.name AS center_name,timeslot.start_time,timeslot.finish_time \n" +
                        "FROM reservation \n" +
                        "JOIN user \n" +
                        "JOIN timeslot \n" +
                        "JOIN center\n" +
                        "JOIN datelist\n" +
                        "ON user.user_id=%s \n" +
                        "\tAND reservation.user_id=user.user_id \n" +
                        "    AND reservation.timeslot_id=timeslot.timeslot_id\n" +
                        "\tAND center.center_id=timeslot.center_id\n" +
                        "    AND datelist.date_id=timeslot.date_id;", userId);
                ResultSet result = s.executeQuery(query);
                Date cur_time = new Date();
                while (result.next()){
                    //compare with current date and time
                    Date timeslot_time =  new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
                            .parse(result.getString("date")+" "+result.getString("finish_time"));
                    //if the endtime is after the current time, future booking
                    if(timeslot_time.compareTo(cur_time)>0){
                        futureList.add(new BookingItem(result.getString("reservation_id"),result.getString("center_name"),result.getString("date")+" "+result.getString("start_time")));
                    }else{
                        historyList.add(new BookingItem(result.getString("reservation_id"),result.getString("center_name"),result.getString("date")+" "+result.getString("start_time")));
                    }
                }
            } catch (Exception e){
                System.out.println("Exception");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            mAdapter = new BookingInformationAdapter(futureList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mHistoryAdapter = new PastBookingInformationAdapter(historyList);
            mHistoryRecyclerView.setLayoutManager(mHistoryLayoutManager);
            mHistoryRecyclerView.setAdapter(mHistoryAdapter);
            super.onPostExecute(aVoid);
        }
    }

}