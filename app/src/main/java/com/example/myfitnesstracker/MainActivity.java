package com.example.myfitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Log Activity button */
    public void goToActivityPage(View view) {
        Intent intent = new Intent(this, MainActivity0.class);
        startActivity(intent);

    }
}



