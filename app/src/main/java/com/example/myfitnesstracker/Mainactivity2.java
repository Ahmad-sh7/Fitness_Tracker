package com.example.myfitnesstracker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private Button button;
    private SeekBar seekBar1;
    private SeekBar seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SeekBar seekBar1 = findViewById(R.id.seekBarOne);
        TextView textView = findViewById(R.id.progress7);
        SeekBar seekBar2 = findViewById(R.id.seekBarTwo);
        TextView textView2 = findViewById(R.id.progress8);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                textView2.setText(String.valueOf(progress2) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button = (Button) findViewById(R.id.zurück2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity0();
            }
        });
        button = (Button) findViewById(R.id.weiter2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    public void openActivity0() {
        Intent intent = new Intent(this, MainActivity0.class);
        startActivity(intent);
    }

    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}
