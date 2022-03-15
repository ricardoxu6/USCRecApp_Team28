package com.example.uscrecapp_team28;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Profile implements ProfileInterface{
    String unique_userid;
    String uscid;
    String username;
    String password;
    String photourl;
    String name;
    String email;
    String device_id;
    ResultSet profile_result;

    public ResultSet getResult() {
        return profile_result;
    }

    public void setResult(ResultSet result) {
        this.profile_result = result;
    }

    public Profile(String did) {
        this.device_id = did;
        // set all other attributes from the database
        try {
            new InitAgentTask().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        ArrayList<String> display = new ArrayList<String>();
        display.add(this.unique_userid);
        display.add(this.uscid);
        display.add(this.username);
        display.add(this.password);
        display.add(this.photourl);
        display.add(this.name);
        display.add(this.email);
        display.add(this.device_id);
        return display;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    class InitAgentTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                String query = String.format("SELECT * FROM user WHERE device_id='%s';", device_id);
                ResultSet result = s.executeQuery(query);
                System.out.println("Query Complete");
                while (result.next()){
                    setUnique_userid(result.getString("user_id"));
                    setUscid(result.getString("usc_id"));
                    setUsername(result.getString("username"));
                    setPassword(result.getString("password"));
                    setPhotourl(result.getString("photourl"));
                    setName(result.getString("name"));
                    setEmail(result.getString("email"));
                }
                setResult(result);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Exception");
            }
            return null;
        }
    }
}
