package com.example.uscrecapp_team28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Agent extends CommonServiceInterface{

    private String device_id;
    private String unique_userid;
    private String username;  // get from user
    private String password;  // get from user
    private String unique_center_id;  // change when click button
    private String unique_timeslot_id;  // change when click button

    // public Agent(String device_id_param) { this.device_id = device_id_param; }
    public Agent() {}

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

    public String getDevice_id() {
        return device_id;
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

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    @Override
    public boolean user_login() {
        LoginCheckerInterface l = new LoginChecker(this.username, this.password);
        boolean x = l.check_login();
        System.out.println(x);
        return x;
    }

    @Override
    public boolean check_loggedin() {
        AuthenticationInterface a = new Authentication(this.device_id);
        return a.if_already_login();
    }

    @Override
    public boolean view_profile() {
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
    public boolean cancel_reservation(String reservation_id) {
        ReservationInterface r = new Reservation();
        r.cancel_reservation(reservation_id);
        return true;
    }

    @Override
    public boolean join_waitlist() {
        return false;
    }

    @Override
    public HashMap<String, ArrayList<BookingItem>> view_all_reservations() {
        ReservationInterface r = new Reservation();
        return r.display_all_reservation_info();
    }
}
