package com.example.uscrecapp_team28;

public class LoginChecker implements LoginCheckerInterface{

    String username;
    String password;

    public LoginChecker(String un, String pw) {
        this.username = un;
        this.password = pw;
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

    @Override
    public boolean check_login() {
        // check database for if username & password combination is correct
        return false;
    }
}
