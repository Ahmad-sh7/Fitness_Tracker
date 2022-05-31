package com.example.myfitnesstracker.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.StatisticsPageActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayAlert(); //called upon app start
    }
    //Tagesabfrage
    public void displayAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tagesabfrage");
        builder.setMessage("Wie oft möchtest Du die Tagesabfrage erhalten?");
        final SeekBar seek = new SeekBar(this);
        seek.setMax(10);
        seek.setKeyProgressIncrement(1);
        builder.setView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                int seekBarValue = seek.getProgress();
                if (seekBarValue == 1) {
                    //fragbatterie um 20 uhr zeigen
                }
                else {
                    //wähle zeitraum wann fragebatterie gezeigt werden soll
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setNegativeButton("Fertig", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
        //Create AlertDialog object
        AlertDialog dialog = builder.create();
        //show the AlertDialog using show() method
        dialog.show();
          
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
