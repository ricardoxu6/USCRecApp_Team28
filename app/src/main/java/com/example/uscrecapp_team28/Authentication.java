package com.example.uscrecapp_team28;

import java.lang.*;

public class Authentication implements AuthenticationInterface{

    String unique_userid;

    public Authentication(String uid) {
        this.unique_userid = uid;
    }

    public String getUnique_userid() {
        return unique_userid;
    }

    public void setUnique_userid(String unique_userid) {
        this.unique_userid = unique_userid;
    }

    @Override
    public boolean if_already_login() {
        // check database for if unique_userid's login column is TRUE
        return false;
    }
}
