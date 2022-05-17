package com.example.myfitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity6 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        button = (Button) findViewById(R.id.zurück5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtoActivity5();
            }
        });
        button = (Button) findViewById(R.id.weiter5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity7();
            }
        });
    }
    public void backtoActivity5() {
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    public void openActivity7() {
        Intent intent = new Intent(this, MainActivity7.class);
        startActivity(intent);
    }
}
