package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private Agent agent_curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        ArrayList<String> profile_result = agent_curr.view_profile();
        String unique_userid = profile_result.get(0);
        String uscid = profile_result.get(1);
        String username = profile_result.get(2);
        String password = profile_result.get(3);
        String photourl = profile_result.get(4);
        String name = profile_result.get(5);
        String email = profile_result.get(6);
        String device_id = profile_result.get(7);
        // change the profile display using these information
    }

    public void onClickBack(View view) {
        System.out.println("BACK TO MAP PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(ProfileActivity.this, MapActivity.class);
        startActivity(i);
        finish();
    }

}