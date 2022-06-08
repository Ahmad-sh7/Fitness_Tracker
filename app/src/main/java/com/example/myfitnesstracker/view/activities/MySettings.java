package com.example.myfitnesstracker.view.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.preference.PreferenceFragmentCompat;

import com.example.myfitnesstracker.R;


public class MySettings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container_view, new MySettingsFragment())
                .commit();
    }
}

