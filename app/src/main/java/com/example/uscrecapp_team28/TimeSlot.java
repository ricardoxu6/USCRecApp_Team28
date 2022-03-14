package com.example.uscrecapp_team28;

import java.util.ArrayList;

public class TimeSlot implements TimeSlotInterface{
    String unique_timeslot_id;
    String timeslot_start_time;
    String timeslot_finish_time;
    String unique_waitlist_id;
    Integer max_capacity;

    public TimeSlot(String unique_timeslot_id, String timeslot_start_time, String timeslot_finish_time, String unique_waitlist_id, int max_capacity) {
        this.unique_timeslot_id = unique_timeslot_id;
        this.timeslot_start_time = timeslot_start_time;
        this.timeslot_finish_time = timeslot_finish_time;
        this.unique_waitlist_id = unique_waitlist_id;
        this.max_capacity = max_capacity;
    }

    public void add_user(String unique_user_id){

    }

    public void delete_user(String unique_user_id){

    }

    @Override
    public void display_timeslot_info() {

    }

    @Override
    public boolean check_availability() {
        return false;
    }

    @Override
    public void notify_waitlist() {

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
}
