package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}