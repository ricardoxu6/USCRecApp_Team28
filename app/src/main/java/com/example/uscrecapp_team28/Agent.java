package com.example.uscrecapp_team28;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Agent extends CommonServiceInterface{

    private String device_id;
    private String unique_userid;
    private String username;  // get from user
    private String password;  // get from user
    private String real_username;
    private String real_password;
    private String uscid;
    private String photourl;
    private String name;
    private String email;

    private String unique_center_id;  // change when click button
    private String unique_timeslot_id;  // change when click button

    private Profile profile = null;

    public Agent() {}

    // init everything in user, should be called when user open map page
    @Override
    public void init_info() {
        profile = new Profile(this.device_id);
        ResultSet result = profile.getResult();
        try {
            while (result.next()) {
                setUnique_userid(result.getString("user_id"));
                setUscid(result.getString("usc_id"));
                setReal_username(result.getString("username"));
                setReal_password(result.getString("password"));
                setPhotourl(result.getString("photourl"));
                setName(result.getString("name"));
                setEmail(result.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public String getReal_username() {
        return real_username;
    }

    public void setReal_username(String real_username) {
        this.real_username = real_username;
    }

    public String getReal_password() {
        return real_password;
    }

    public void setReal_password(String real_password) {
        this.real_password = real_password;
    }

    public String getUscid() {
        return uscid;
    }

    public void setUscid(String uscid) {
        this.uscid = uscid;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean user_login() {
        LoginCheckerInterface l = new LoginChecker(this.username, this.password, this.device_id);
        boolean correct = l.check_login();
        return correct;
    }

    @Override
    public boolean check_loggedin() {
        AuthenticationInterface a = new Authentication(this.device_id);
        return a.if_already_login();
    }

    @Override
    public ArrayList<String> view_profile() {
        // make sure the profile is not empty
        while (this.profile == null) {
            init_info();
        }
        return profile.display_profile();
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

    @Override
    public String toString() {
        return unique_userid + uscid + real_username + real_password + photourl + name + email + device_id;
    }
}
