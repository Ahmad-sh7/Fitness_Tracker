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
        displayAlert(); //called upon app start
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
    //Tagesabfrage
    public void displayAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((getResources().getString(R.string._daily_query)));
        builder.setMessage(getResources().getString(R.string._daily_question_query));
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
