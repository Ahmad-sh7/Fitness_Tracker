package com.example.myfitnesstracker.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.model.ActivtyRecord;
import com.example.myfitnesstracker.model.AppDatabase;
import com.example.myfitnesstracker.model.SensorData;
import com.example.myfitnesstracker.model.dao.SensorDataDao;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitiesPageActivity extends AppCompatActivity implements SensorEventListener,OnClickListener {
    private SensorManager sensorManager;
    private Sensor Accelerometer;
    Handler handler;
    int interval = 6000; //read sensor data every 1 minute = 6000 ms
    boolean flag = false; //initialized
    TextView txt_x; //declare x axis object
    TextView txt_y; //declare y axis object
    TextView txt_z; //declare z axis object

    private double accelerationCurrentValue;
    private double accelerationPreviousValue;
    private double changedAcceleration;

    ArrayList<SensorData> periodicSensorData = new ArrayList<>();

    Button startButton;
    Button stopButton;
    Spinner spinner;
    AppDatabase db;





    private final Runnable processSensors = new Runnable() {
        @Override
        public void run() {
            // Do work with the sensor values.

            //flag = true;
            // The Runnable is posted to be run after the specified amount of time elapses
            handler.postDelayed(this, interval);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_page);
        db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"sensordb").build();


       spinner = (Spinner) findViewById(R.id.spinner_activities);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.listActivities, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        startButton = (Button) findViewById(R.id.start);
        stopButton = (Button) findViewById(R.id.stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        handler = new Handler();

        //txt_x = findViewById(R.id.textView2); //create x axis object
        //txt_y = findViewById(R.id.textView3); // create y axis object
        //txt_z = findViewById(R.id.textView4); //create z axis object






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
    Boolean isFirstTime=true;
    Timer timer=new Timer();
    Timer timer2 = new Timer();


    public void onSensorChanged(SensorEvent sensorEvent) {

        //assign directions
        if (flag) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            accelerationCurrentValue = Math.sqrt(x * x + y * y + z * z);

            double changedAcceleration = Math.abs(accelerationCurrentValue - accelerationPreviousValue);
            accelerationPreviousValue = accelerationCurrentValue;

            //txt_x.setText(String.format("Current =%s", accelerationCurrentValue));
            //txt_y.setText(String.format("prev =%s", accelerationPreviousValue));
            //txt_z.setText(String.format("changed =%s", changedAcceleration));

            if (isFirstTime){
                int begin=0;
                int timeInterval =1000;
                timer.schedule(new TimerTask() {
                    int counter=0;
                    @Override
                    public void run() {
                        periodicSensorData.add(new SensorData(
                                System.currentTimeMillis(),
                                x,y,z
                        ));

                    }
                },begin,2000);
                isFirstTime=false;
            }




        }else{
            //txt_x.setText("Waiting for sensor data..");
            //txt_y.setText("Waiting for sensor data..");
            //txt_z.setText("Waiting for sensor data..");

        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        //empty for now

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                timer= new Timer();
                timer2= new Timer();
                isFirstTime = true;

                sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                flag=true;
                dialogTimer.start();

                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                SensorDataDao sensorDataDao=db.sensorDataDao();
                                String[] arrayActivities = getResources().getStringArray(R.array.listActivities);
                                sensorDataDao.insertAll(new ActivtyRecord(
                                        periodicSensorData, arrayActivities[spinner.getSelectedItemPosition()],System.currentTimeMillis(),System.currentTimeMillis()
                                ));
                                sensorDataDao.getAll();
                                periodicSensorData.clear();

                            }
                        });
                    }
                },0,60000);


                break;
            case R.id.stop:
                sensorManager.unregisterListener(this, Accelerometer);
                sensorManager.unregisterListener(this);
                startButton.setEnabled(true);
                stopButton.setEnabled(false);
                flag=false;
                timer.cancel();
                timer2.cancel();
                dialogTimer.cancel();




                break;
        }
    }
    void exitActivity() {
        sensorManager.unregisterListener(this, Accelerometer);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        flag=false;
    }

    void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erinnerung");
        builder.setMessage("Bist Du schon fertig?");
        //add action buttons
        builder.setPositiveButton("JA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                exitActivity();
                //fragebatterie
            }
        });
        builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
        //Create AlertDialog object
        AlertDialog alertDialog = builder.create();
        //show the AlertDialog using show() method
        alertDialog.show();

    }

    // 10800000ms = 3 hours

    final CountDownTimer dialogTimer = new CountDownTimer(10800000, 1000) {
        public void onTick(long millisUntilFinished) {
        }
        public void onFinish () {
            if (flag == true) {
                showAlertDialog();
            }
        }

    };

}
