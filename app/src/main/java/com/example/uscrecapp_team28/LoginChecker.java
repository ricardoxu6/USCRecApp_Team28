package com.example.uscrecapp_team28;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginChecker implements LoginCheckerInterface{

    String username;
    String password;
    Boolean loginFlag;

    public LoginChecker(String un, String pw) {
        this.username = un;
        this.password = pw;
        this.loginFlag = false;
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

    public boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean b) {
        this.loginFlag = b;
    }

    @Override
    public boolean check_login() {
        // check database for if username & password combination is correct
        try {
            new LoginTask().execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(this.loginFlag);
        if (this.loginFlag) {
            return true;
        }
        else {
            return false;
        }
    }

    class LoginTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String connectionUrl = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3479112?characterEncoding=latin1";
                Connection connection = DriverManager.getConnection(connectionUrl,"sql3479112","k1Q9Fq3375");
                Statement s = connection.createStatement();
                String query = String.format("SELECT * FROM user WHERE username=\"%s\" AND password=\"%s\";", username, password);
                ResultSet result = s.executeQuery(query);
                System.out.println("Query Complete");
                System.out.println(result);
                while (result.next()) {
                    setLoginFlag(true);
                    return null;
                }
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Exception");
            }
            return null;
        }
    }
}
