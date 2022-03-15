package com.example.uscrecapp_team28;

import java.util.ArrayList;

public class Profile implements ProfileInterface{

    String unique_userid;
    String uscid;
    String username;
    String password;
    String photourl;
    String name;
    String email;
    String device_id;

    public Profile(String uid) {
        this.unique_userid = uid;
        // set all other attributes from the database
    }

    public String getUnique_userid() {
        return unique_userid;
    }

    public void setUnique_userid(String unique_userid) {
        this.unique_userid = unique_userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public ArrayList<String> display_profile() {
        return null;
    }
}
