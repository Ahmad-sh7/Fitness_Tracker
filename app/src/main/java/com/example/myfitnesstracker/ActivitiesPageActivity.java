package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitiesPageActivity extends AppCompatActivity implements SensorEventListener,OnClickListener {
    private SensorManager sensorManager;
    private Sensor Accelerometer;
    Handler handler;
    int interval = 60000; //read sensor data every 1 minute = 6000 ms
    boolean flag = false; //initialized
    TextView txt_x; //declare x axis object
    TextView txt_y; //declare y axis object
    TextView txt_z; //declare z axis object

    private double accelerationCurrentValue;
    private double accelerationPreviousValue;
    private double changedAcceleration;


    private final Runnable processSensors = new Runnable() {
        @Override
        public void run() {
            // Do work with the sensor values.

            flag = true;
            // The Runnable is posted to be run after the specified amount of time elapses
            handler.postDelayed(this, interval);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_page);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_activities);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.listActivities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Button startButton = (Button) findViewById(R.id.start);
        Button stopButton = (Button) findViewById(R.id.stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        handler = new Handler();

        txt_x = findViewById(R.id.textView2); //create x axis object
        txt_y = findViewById(R.id.textView3); // create y axis object
        txt_z = findViewById(R.id.textView4); //create z axis object


        //create the sensor manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //create the accelerometer
        Accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        handler.post(processSensors); //puts the Runnable in a message queue
    }

    protected void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(this);
        handler.removeCallbacks(processSensors); // Remove any pending posts of Runnable that are in the message queue.
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        //assign directions
        if (flag) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelerationCurrentValue = Math.sqrt(x * x + y * y + z * z);

            double changedAcceleration = Math.abs(accelerationCurrentValue - accelerationPreviousValue);
            accelerationPreviousValue = accelerationCurrentValue;

            txt_x.setText(String.format("Current =%s", accelerationCurrentValue));
            txt_y.setText(String.format("prev =%s", accelerationPreviousValue));
            txt_z.setText(String.format("changed =%s", changedAcceleration));

            flag = false;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        //empty for now

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.stop:
                sensorManager.unregisterListener(this, Accelerometer);
                sensorManager.unregisterListener(this);
                break;
        }
    }
}
