package com.example.uscrecapp_team28;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class CommonServiceInterface implements ActivitySwitchingHelperInterface {
    public boolean to_login_page() {
        return false;
    }

    public boolean to_main_page() {
        return false;
    }

    public boolean to_profile_page() {
        return false;
    }

    public boolean to_booking_page() {
        return false;
    }

    public boolean to_summary_page() {
        return false;
    }

    abstract public boolean user_login();
    abstract public boolean check_loggedin();
    abstract public ArrayList<String> view_profile();
    abstract public boolean view_all_timeslots();
    abstract public boolean make_reservation();
    abstract public boolean cancel_reservation(String reservation_id);
    abstract public boolean join_waitlist();
    abstract public HashMap<String, ArrayList<BookingItem>> view_all_reservations();
    abstract public void init_info();
}
