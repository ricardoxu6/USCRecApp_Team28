package com.example.uscrecapp_team28;
import java.util.ArrayList;

public interface TimeSlotInterface {
    public ArrayList<TimeslotItem> display_all_timeslot_info(String center_id, String thisdate);
    public boolean check_availability();
    public void notify_waitlist();
}
