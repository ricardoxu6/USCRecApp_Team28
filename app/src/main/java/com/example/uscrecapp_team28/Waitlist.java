package com.example.uscrecapp_team28;

import java.util.ArrayList;

public class Waitlist implements WaitlistInterface{
    String unique_waitlist_id;
    String unique_timeslot_id;
    ArrayList<String> user_id_array;

    public Waitlist(String unique_waitlist_id, String unique_timeslot_id, ArrayList<String> user_id_array) {
        this.unique_waitlist_id = unique_waitlist_id;
        this.unique_timeslot_id = unique_timeslot_id;
        this.user_id_array = user_id_array;
    }

    public Boolean waitlist_user(String user_id){
        return false;
    }

    @Override
    public void notify_all_in_waitlist() {

    }

    public String getUnique_waitlist_id() {
        return unique_waitlist_id;
    }

    public void setUnique_waitlist_id(String unique_waitlist_id) {
        this.unique_waitlist_id = unique_waitlist_id;
    }

    public String getUnique_timeslot_id() {
        return unique_timeslot_id;
    }

    public void setUnique_timeslot_id(String unique_timeslot_id) {
        this.unique_timeslot_id = unique_timeslot_id;
    }

    public ArrayList<String> getUser_id_array() {
        return user_id_array;
    }

    public void setUser_id_array(ArrayList<String> user_id_array) {
        this.user_id_array = user_id_array;
    }
}
