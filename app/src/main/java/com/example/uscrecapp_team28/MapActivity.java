package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MapActivity extends AppCompatActivity {

    private Agent agent_curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        this.agent_curr = ((MyApplication) this.getApplication()).getAgent();
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        agent_curr.init_info();
    }

    public void onClickProfile(View view) {
        System.out.println("TO PROFILE PAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickLyon(View view) {
        System.out.println("TO LYON");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingActivity.class);
        i.putExtra("gym", "lyon");
        startActivity(i);
        finish();
    }

    public void onClickVill(View view) {
        System.out.println("TO VILLAGE");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingActivity.class);
        i.putExtra("gym", "village");
        startActivity(i);
        finish();
    }

    public void onClickSummary(View view) {
        System.out.println("TO SUMMARY");
        if (!this.agent_curr.check_loggedin()) {
            Intent i = new Intent(MapActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        Intent i = new Intent(MapActivity.this, BookingInformationActivity.class);
        startActivity(i);
        finish();
    }
}