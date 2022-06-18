package com.example.myfitnesstracker.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AlertDialog;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.StatisticsPageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends LocalizationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton mySettings = (FloatingActionButton) findViewById(R.id.my_settings);
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        if (settings.getString("lang","de").equals("en")){
            setLanguage("en");
        }else{
            setLanguage("de");
        }

        mySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MySettings.class));
            }
        });
    }

    /** Called when the user taps the Log Activity button */
    public void goToActivityPage(View view) {
        Intent intent = new Intent(this, com.example.myfitnesstracker.view.activities.ActivitiesPageActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the My Statistics button */
    public void goToStatisticsPage(View view) {
        Intent intent = new Intent(this, StatisticsPageActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the My Statistics button */
    public void goToLogMoodPage(View view) {
        Intent intent = new Intent(this, MainActivity0.class);
        startActivity(intent);
    }


}
