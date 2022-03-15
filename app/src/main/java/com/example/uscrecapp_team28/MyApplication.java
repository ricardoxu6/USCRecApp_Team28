package com.example.uscrecapp_team28;

import android.app.Application;

public class MyApplication extends Application {

    private Agent global_agent = new Agent();

    public Agent getAgent() {
        return this.global_agent;
    }

    public void setAgent(Agent agent) {
        this.global_agent = agent;
    }
}
