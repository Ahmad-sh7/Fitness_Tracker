package com.example.myfitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {
    private Button button;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        button = (Button) findViewById(R.id.zurück6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoActivity6();
            }
        });
        button = (Button) findViewById(R.id.weiter6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity8();
            }
        });
        ratingBar1 = findViewById(R.id.ratingBar1);
        ratingBar1.setNumStars(9);
        ratingBar1.setStepSize((float) 1);
        button = (Button) findViewById(R.id.abgeben1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar1.getRating());
                Toast.makeText(getApplicationContext(), s+"Star", Toast.LENGTH_SHORT).show();
            }
        });
        ratingBar2 = findViewById(R.id.ratingBar2);
        ratingBar2.setNumStars(9);
        ratingBar2.setStepSize((float) 1);
        button = (Button) findViewById(R.id.abgeben2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = String.valueOf(ratingBar2.getRating());
                Toast.makeText(getApplicationContext(), s+"Star", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backtoActivity6() {
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
    }

    public void openActivity8() {
        Intent intent = new Intent(this, MainActivity8.class);
        startActivity(intent);
    }
}
