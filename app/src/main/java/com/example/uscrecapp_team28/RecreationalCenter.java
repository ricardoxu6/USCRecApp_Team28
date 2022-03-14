package com.example.uscrecapp_team28;

import java.util.ArrayList;

public class RecreationalCenter implements RecreationalCenterInterface{
    String unique_center_id;
    String center_name;
    String location;
    ArrayList<String> timeslot_id_list;

    /**
     * Constructor of Recreational Center
     * @param unique_center_id
     * @param center_name
     * @param location
     * @param timeslot_id_list
     */
    public RecreationalCenter(String unique_center_id, String center_name, String location, ArrayList<String> timeslot_id_list) {
        this.unique_center_id = unique_center_id;
        this.center_name = center_name;
        this.location = location;
        this.timeslot_id_list = timeslot_id_list;
    }

    /**
     * Getter for center id
     * @return unique_center_id
     */
    public String getUnique_center_id() {
        return unique_center_id;
    }

    /**
     * Setter for center id
     * @param unique_center_id
     */
    public void setUnique_center_id(String unique_center_id) {
        this.unique_center_id = unique_center_id;
    }
    /**
     * Getter for center name
     * @return center_name
     */
    public String getCenter_name() {
        return center_name;
    }

    /**
     * Setter for center name
     * @param center_name
     */
    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }
    /**
     * Getter for center location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for center location
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * Getters for the list of timeslots' id
     * @return timeslot_id_list
     */
    public ArrayList<String> getTimeslot_id_list() {
        return timeslot_id_list;
    }

    /**
     * Setter for timeslot_id_list
     * @param timeslot_id_list
     */
    public void setTimeslot_id_list(ArrayList<String> timeslot_id_list) {
        this.timeslot_id_list = timeslot_id_list;
    }

    @Override
    public void display_all_slots() {


    }

}
