package com.example.uscrecapp_team28;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class BookingAdapter extends RecyclerView.Adapter{
    private ArrayList<TimeslotItem> tSlotList;
    private Agent tAgent;

    Context context;
    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public AppCompatButton bookButton;
        public AppCompatButton waitButton;

        public BookingViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            bookButton = itemView.findViewById(R.id.BookButton);
            waitButton = itemView.findViewById(R.id.WaitButton);
        }
    }

    public BookingAdapter(ArrayList<TimeslotItem> slotList, Agent agent) {
        System.out.println("Constructor for Booking Adapter");
        tSlotList = slotList;
        tAgent = agent;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeslot_item, parent, false);
        context = parent.getContext();
        BookingViewHolder evh = new BookingViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("On Bind View Holder");
        TimeslotItem currentItem = tSlotList.get(position);
        String times = currentItem.getDate() + "  " + currentItem.getStart() + " - " + currentItem.getFinish();
        ((BookingViewHolder) holder).mTextView1.setText(times);
        int remain_spot = currentItem.getMax_cap() - currentItem.getCurrent_users();
        ((BookingViewHolder) holder).mTextView2.setText(Integer.toString(remain_spot));
        if(remain_spot > 0){
            ((BookingViewHolder) holder).waitButton.setVisibility(View.INVISIBLE);
            ((BookingViewHolder) holder).bookButton.setTag(currentItem.getTimeslot_id());
            ((BookingViewHolder) holder).bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user_id = currentItem.getUser_id();
                    String time_id = Integer.toString(currentItem.getTimeslot_id());
                    System.out.println(user_id);
                    System.out.println(time_id);
                    try {
                        new MakeBooking(time_id, user_id, currentItem.getDate_id(), currentItem.getMax_cap()).execute().get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(context, BookingActivity.class);
                    i.putExtra("gym", currentItem.getCenter_id());
                    i.putExtra("datechoice", currentItem.getDate());
                    context.startActivity(i);
                }
            });
        }
        else{
            ((BookingViewHolder) holder).bookButton.setVisibility(View.INVISIBLE);
            ((BookingViewHolder) holder).waitButton.setTag(currentItem.getTimeslot_id());
            ((BookingViewHolder) holder).waitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String user_id = currentItem.getUser_id();
                    String time_id = Integer.toString(currentItem.getTimeslot_id());
                    try {
                        new WaitBooking(time_id, user_id).execute().get();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(context, BookingActivity.class);
                    i.putExtra("gym", currentItem.getCenter_id());
                    i.putExtra("datechoice", currentItem.getDate());
                    context.startActivity(i);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return tSlotList.size();
    }

    //Join Waitlist
    class WaitBooking extends AsyncTask<Void, Void, Void> {
        String time_id;
        String user_id;
        //set parameter for async function
        public WaitBooking(String time_id, String user_id){
            this.time_id = time_id;
            this.user_id = user_id;
        }
        @Override
        protected Void doInBackground(Void... voids){
            try{
                System.out.println("enter background");
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                System.out.println("after connection");
                //query the database for all user's reservation
                String query = String.format(
                        "INSERT INTO waitlist(user_id, timeslot_id) VALUES (%s, %s);", user_id, time_id);
                int result = s.executeUpdate(query);
                if(result == 1){
                    System.out.println("Waitlist Insertion Succeeds");
                }
            } catch (Exception e){
                System.out.println("Exception");
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }

    //Make the booking
    class MakeBooking extends AsyncTask<Void, Void, Void> {
        String time_id;
        String user_id;
        String date_id;
        int capacity;
        //set parameter for async function
        public MakeBooking(String time_id, String user_id, String date_id, int capacity){
            this.time_id = time_id;
            this.user_id = user_id;
            this.date_id = date_id;
            this.capacity = capacity;
        }
        @Override
        protected Void doInBackground(Void... voids){
            try{
                System.out.println("enter background");
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                System.out.println("after connection");
                //query the database for all user's reservation
                String judge = String.format(
                        "SELECT * FROM availability WHERE user_id = %s AND date_id = %s ;", user_id, date_id);
                ResultSet result1 = s.executeQuery(judge);
                Boolean flag1 = true;
                while(result1.next()){
                    flag1 = false;
                    break;
                }
                if(flag1 == true){
                    String lastcheck = String.format(
                            "SELECT COUNT(reservation.timeslot_id) AS count FROM reservation WHERE reservation.timeslot_id = %s;",  time_id);
                    ResultSet result2 = s.executeQuery(lastcheck);
                    int count = 0;
                    while(result2.next()){
                        count = result2.getInt("count");
                    }
                    int remaining = capacity - count;
                    //replace with a message box later
                    if(remaining <= 0){
                        System.out.println("No spots left!!");
                    }
                    else{
                        String query = String.format(
                                "INSERT INTO reservation(user_id, timeslot_id) VALUES (%d, %s);", Integer.parseInt(user_id), time_id);
                        String query2 = String.format(
                                "INSERT INTO availability(user_id, date_id) VALUES (%d, %s);", Integer.parseInt(user_id), date_id);
                        System.out.println(query);
                        int result = s.executeUpdate(query);
                        int result3 = s.executeUpdate(query2);
                        if(result == 1 && result3 ==1){
                            System.out.println("Reservation Insertion Succeeds");
                        }
                    }
                }
                else{
                    //Replace with a messagebox later.
                    System.out.println("You already have a reservation today.");
                }

            } catch (Exception e){
                System.out.println("Exception");
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }
}
