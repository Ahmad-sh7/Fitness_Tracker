package com.example.myfitnesstracker.view.activities;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfitnesstracker.R;
import com.example.myfitnesstracker.databinding.ActivityQueryBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class QueryActivity extends AppCompatActivity {
private ActivityQueryBinding binding;
private MaterialTimePicker picker;
Calendar calendar;
AlarmManager alarmManager;
PendingIntent pendingIntent;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        binding = ActivityQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createNotificationChannel();
        calendar = new Calendar() {
            @Override
            protected void computeTime() {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            public void add(int i, int i1) {

            }

            @Override
            public void roll(int i, boolean b) {

            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }
        };

        binding.SetReminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();

            }
        });

        binding.CancelReminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();

            }

        });

        binding.SelectTimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();

            }
        });

        Button cancelAll = findViewById(R.id.CancelAll);
        cancelAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QueryActivity.this, "Alarms Cancelled", Toast.LENGTH_LONG).show();
                cancelAlarm1();
                cancelAlarm2();
                cancelAlarm3();
                cancelAlarm4();
                cancelAlarm5();
                cancelAlarm6();
                cancelAlarm7();
                cancelAlarm8();
                cancelAlarm9();
                cancelAlarm10();
            }

        });

        //Button queryButton = findViewById(R.id.button3);
        //queryButton.setOnClickListener(view -> displayAlert());
        SeekBar seekbar = (findViewById(R.id.your_dialog_seekbar));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //int seekBarValue = seek.getProgress();
                if (progress == 1) {
                    //fragbatterie um 20 uhr und dann pop up message als bestätigung zeigen
                    CreateAlarm8();
                }

                if (progress == 2) {
                    CreateAlarm2(); //8 am
                    CreateAlarm8(); //8 pm
                }

                if (progress == 3) {
                    CreateAlarm2(); // 8 am
                    CreateAlarm5(); // 2 pm
                    CreateAlarm8(); // 8 pm
                }

                if (progress == 4) {
                    CreateAlarm2(); // 8 am
                    CreateAlarm4(); // 12 pm
                    CreateAlarm6(); // 16 pm
                    CreateAlarm9(); //10 pm
                }

                if (progress == 5) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                }

                if (progress == 6) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                }

                if (progress == 7) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                }
                if (progress == 8) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                }
                if (progress == 9) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                    CreateAlarm9();
                }
                if (progress == 10) {
                    CreateAlarm1();
                    CreateAlarm2();
                    CreateAlarm3();
                    CreateAlarm4();
                    CreateAlarm5();
                    CreateAlarm6();
                    CreateAlarm7();
                    CreateAlarm8();
                    CreateAlarm9();
                    CreateAlarm10();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

//Create notification channel to group alarm manager notifications together
    private void createNotificationChannel() {
        if(android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O) {
            CharSequence name = "androidReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("android", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
//Create time picker
    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(), "android");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(picker.getHour() > 12) {
                    binding.selectedTime.setText(picker.getHour()%12 + " : " + picker.getMinute() + " AM");
                } else{
                    binding.selectedTime.setText(picker.getHour() + " : " + picker.getMinute() + " AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND, 0);


            }
        });

    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_LONG).show();
    }
//Create methods to cancel all the 10 alarms
    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_LONG).show();

    }

    private void cancelAlarm1(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id1 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id1, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm2(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id2 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id2, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm3(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id3 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id3, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm4(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id4 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id4, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm5(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id5 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id5, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm6(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id6 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id6, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm7(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id7 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id7, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm8(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id8 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id8, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm9(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id9 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id9, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    private void cancelAlarm10(){
        Intent intent = new Intent(this, AlarmReceiver.class);
        final int id10 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id10, intent,0);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(alarmIntent);
    }

    //Create 10 alarms to distribute over the day
    public void CreateAlarm1() {
        // set alarm at approx. 06:00 pm
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id1 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id1, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 06:00 Uhr zeigen!", Toast.LENGTH_LONG).show();
    }

    public void CreateAlarm2() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,22 );
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id2 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id2, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 08:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm3() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id3 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id3, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 10:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm4() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 5);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id4 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id4, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 12:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm5() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id5 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id5, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 14:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm6() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id6 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id6, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 16:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm7() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,18 );
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id7 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id7, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 18:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm8() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id8 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id8, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 20:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm9() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 0);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id9 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id9, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 22:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

    public void CreateAlarm10() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 48);
        Intent intent = new Intent(this , AlarmReceiver.class);
        AlarmManager alarmMgr;
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final int id10 = (int) System.currentTimeMillis(); //make the pending intent unique
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, id10, intent,0);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
        Toast.makeText(this, "Erinnerung um 24:00 Uhr zeigen!", Toast.LENGTH_LONG).show();

    }

}

