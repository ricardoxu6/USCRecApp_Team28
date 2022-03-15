package com.example.uscrecapp_team28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void onClickBack(View view) {
        System.out.println("BACK TO MAP PAGE");
        Intent i = new Intent(ProfileActivity.this, MapActivity.class);
        startActivity(i);
        finish();
    }

}