package com.example.myfitnesstracker.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnesstracker.R;


public class MySettings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container_view, new com.example.myfitnesstracker.view.activities.MySettingsFragment())
                .commit();
    }
}

