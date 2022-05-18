package com.example.myfitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity0();
            }
        });
    }

    public void openActivity0() {
        Intent intent = new Intent(this, MainActivity0.class);
        startActivity(intent);
    }

    /** Called when the user taps the Log Activity button
     *
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 goToActivityPage();
             }
         });
     public void goToActivityPage(View view) {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
     }
     */
}
