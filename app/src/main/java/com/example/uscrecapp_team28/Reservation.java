package com.example.uscrecapp_team28;

import java.util.ArrayList;

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

    @Override
    // return all necessary information to display all the booking information in app
    public ArrayList<String> display_all_reservation_info() {
        return null;
    }
}
