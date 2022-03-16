package com.example.uscrecapp_team28;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

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
import java.util.concurrent.ExecutionException;


public class Reservation implements ReservationInterface{

    String unique_userid;

    public Reservation(String uid) {
        this.unique_userid = uid;
    }

    public String getUnique_userid() {
        return unique_userid;
    }

    public void setUnique_userid(String unique_userid) {
        this.unique_userid = unique_userid;
    }

    private final HashMap<String, ArrayList<BookingItem>> listMap = new HashMap<String,ArrayList<BookingItem>>(){{
        put("future",new ArrayList<>());
        put("history", new ArrayList<>());
    }};
    @Override
    // return all necessary information to display all the booking information in app
    public HashMap display_all_reservation_info() {
        try {
            new GetBookingInformation().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return listMap;
    }

    @Override
    public void cancel_reservation(String reservation_id) {
        //cancel the reservation from sql database
        try {
            new CancelBooking(reservation_id).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //else do not need to notify

    }

    //cancel the booking information based on reservation_id
    class CancelBooking extends AsyncTask<Void, Void, Void> {
        String reservation_id;

        //set parameter for async function
        public CancelBooking(String reservation_id){
            this.reservation_id = reservation_id;
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
                //update the database by delete the reservation
                String query = String.format(
                        "DELETE from reservation\n" +
                                "\tWHERE reservation.reservation_id=%s;", reservation_id);

                s.executeUpdate(query);
                System.out.println(String.format("after execution of query delete %s from database",reservation_id));
                //also notify the user
                connection.close();
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
    class GetBookingInformation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids){
            try{
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
//              String userId = sp1.getString("user_id",null);
                // String userId = "0";
                String userId = getUnique_userid();
                System.out.println("User Unique ID: " + userId);
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
                        listMap.get("future").add(new BookingItem(result.getString("reservation_id"),result.getString("center_name"),result.getString("date")+" "+result.getString("start_time")));
                    }else{
                        listMap.get("history").add(new BookingItem(result.getString("reservation_id"),result.getString("center_name"),result.getString("date")+" "+result.getString("start_time")));
                    }
                }
                connection.close();
            } catch (Exception e){
                System.out.println("Exception");
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }
}
