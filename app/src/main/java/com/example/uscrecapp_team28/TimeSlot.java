package com.example.uscrecapp_team28;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class TimeSlot implements TimeSlotInterface{
    String unique_timeslot_id;
    String timeslot_start_time;
    String timeslot_finish_time;
    String unique_waitlist_id;
    String center_id;
    Integer max_capacity;

    private ArrayList<TimeslotItem> slotList = new ArrayList<TimeslotItem>();


    public TimeSlot(){};

    public TimeSlot(String unique_timeslot_id, String timeslot_start_time, String timeslot_finish_time, String unique_waitlist_id, int max_capacity, String center_id) {
        this.unique_timeslot_id = unique_timeslot_id;
        this.timeslot_start_time = timeslot_start_time;
        this.timeslot_finish_time = timeslot_finish_time;
        this.unique_waitlist_id = unique_waitlist_id;
        this.max_capacity = max_capacity;
        this.center_id = center_id;
    }

    public void add_user(String unique_user_id){

    }

    public void delete_user(String unique_user_id){

    }



    @Override
    public ArrayList<TimeslotItem> display_all_timeslot_info(String center_id, String thisdate) {
        setCenter_id(center_id);
        try {
            new GetTimeslotInformation("1", thisdate).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return slotList;
    }

    @Override
    public boolean check_availability() {
        return false;
    }

    @Override
    public void notify_waitlist() {

    }

    class GetTimeslotInformation extends AsyncTask<Void, Void, Void> {
        String usid;
        String datenow;

        public GetTimeslotInformation(String usid, String thisdate) {
            this.usid = usid;
            this.datenow = thisdate;
        }

        @Override
        protected Void doInBackground(Void... voids){
            try{
                //connect to sql database
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                String centerId = center_id;
                System.out.println(centerId);
                System.out.println(datenow);
                //query the database for all user's reservation
                String query = String.format("SELECT \n" +
                        "\ttimeslot.timeslot_id AS timeslot_id,timeslot.max_capacity AS maxcap,timeslot.date_id, center.name AS center_name,timeslot.start_time,timeslot.finish_time, " +
                        "COUNT(reservation.timeslot_id) AS currentusers\n" +
                        "FROM timeslot \n" +
                        "JOIN center\n" +
                        "ON center.center_id = timeslot.center_id \n" +
                        "LEFT JOIN reservation \n" +
                        "\tON reservation.timeslot_id=timeslot.timeslot_id \n" +
                        "JOIN datelist\n" +
                        " ON datelist.date_id=timeslot.date_id WHERE datelist.date = '%s' AND center.center_id=%s " +
                        "GROUP BY(timeslot.timeslot_id) ;", datenow, centerId);
                System.out.println(query);
                ResultSet result = s.executeQuery(query);
                while (result.next()){
                    slotList.add(new TimeslotItem(Integer.parseInt(result.getString("timeslot_id")),datenow,Integer.parseInt(result.getString("currentusers")), Integer.parseInt(result.getString("maxcap")),
                            result.getString("start_time"), result.getString("finish_time"), result.getString("center_name"), usid, centerId, result.getString("date_id")));
                }
                System.out.println("Get all timeslots");
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

    public String getUnique_timeslot_id() {
        return unique_timeslot_id;
    }

    public void setUnique_timeslot_id(String unique_timeslot_id) {
        this.unique_timeslot_id = unique_timeslot_id;
    }

    public String getTimeslot_start_time() {
        return timeslot_start_time;
    }

    public void setTimeslot_start_time(String timeslot_start_time) {
        this.timeslot_start_time = timeslot_start_time;
    }

    public String getTimeslot_finish_time() {
        return timeslot_finish_time;
    }

    public void setTimeslot_finish_time(String timeslot_finish_time) {
        this.timeslot_finish_time = timeslot_finish_time;
    }

    public String getUnique_waitlist_id() {
        return unique_waitlist_id;
    }

    public void setUnique_waitlist_id(String unique_waitlist_id) {
        this.unique_waitlist_id = unique_waitlist_id;
    }

    public Integer getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(Integer max_capacity) {
        this.max_capacity = max_capacity;
    }

    public String getCenter_id() {
        return center_id;
    }

    public void setCenter_id(String center_id) {
        this.center_id = center_id;
    }
}
