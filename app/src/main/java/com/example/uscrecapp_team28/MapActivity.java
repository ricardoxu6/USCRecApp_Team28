package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // this method relocate the two buttons (two gyms) relative to different screens
        super.onWindowFocusChanged(hasFocus);
        ImageView imgv = (ImageView) findViewById(R.id.uscmap);
        System.out.println(imgv.getWidth());
        System.out.println(imgv.getHeight());
        // lyon
        Button bt1 = (Button) findViewById(R.id.button1);
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) bt1.getLayoutParams();
        layoutParams1.leftMargin = imgv.getWidth() * 350 / 756 - layoutParams1.height;
        layoutParams1.topMargin = imgv.getHeight() * 337 / 1012 - layoutParams1.height;
        bt1.setLayoutParams(layoutParams1);
        // village
        Button bt2 = (Button) findViewById(R.id.button2);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) bt2.getLayoutParams();
        layoutParams2.leftMargin = imgv.getWidth() * 553 / 756 - layoutParams2.height;
        layoutParams2.topMargin = imgv.getHeight() * 287 / 1012 - layoutParams2.height;
        bt2.setLayoutParams(layoutParams2);
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
        i.putExtra("gym", 1);
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
        i.putExtra("gym", 2);
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