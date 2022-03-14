package com.example.uscrecapp_team28;

public class Agent extends CommonServiceInterface{

    String unique_userid;
    String username;  // get from user
    String password;  // get from user
    String unique_center_id;  // change when click button
    String unique_timeslot_id;  // change when click button

    public Agent(String uid) {
        this.unique_userid = uid;
    }

    public String getUnique_userid() {
        return unique_userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUnique_center_id() {
        return unique_center_id;
    }

    public String getUnique_timeslot_id() {
        return unique_timeslot_id;
    }

    public void setUnique_userid(String unique_userid) {
        this.unique_userid = unique_userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUnique_center_id(String unique_center_id) {
        this.unique_center_id = unique_center_id;
    }

    public void setUnique_timeslot_id(String unique_timeslot_id) {
        this.unique_timeslot_id = unique_timeslot_id;
    }

    @Override
    public boolean user_login() {
        return false;
    }

    @Override
    public boolean check_profile() {
        return false;
    }

    @Override
    public boolean view_all_timeslots() {
        return false;
    }

    @Override
    public boolean make_reservation() {
        return false;
    }

    @Override
    public boolean cancel_reservation() {
        return false;
    }

    @Override
    public boolean join_waitlist() {
        return false;
    }

    @Override
    public boolean view_all_reservations() {
        return false;
    }
}
