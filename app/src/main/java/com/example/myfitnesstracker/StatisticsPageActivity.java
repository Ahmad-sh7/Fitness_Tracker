package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.*;

public class StatisticsPageActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialize Variables
    BarChart barChartActivity;
    LineChart lineChartMood;
    Button button7days;
    Button button30days;
    Button button90days;
    Button button365days;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        // Assign Variables
        barChartActivity = findViewById(R.id.bar_chart_activity);
        lineChartMood = findViewById(R.id.line_chart_mood);
        button7days = findViewById(R.id.button_7_days);
        button30days = findViewById(R.id.button_30_days);
        button90days = findViewById(R.id.button_90_days);
        button365days = findViewById(R.id.button_365_days);
        button7days.setOnClickListener(this);
        button30days.setOnClickListener(this);
        button90days.setOnClickListener(this);
        button365days.setOnClickListener(this);
        db = new DBHandler(this);

        makeBarChart(7);
        makeLineChart(7);
        makeExampleDbEntries(7);
    }

    @Override
    public void onClick(View v)
    {
        int days = 7;
        //if(v.getId() == R.id.button_7_days){days = 7;}
        if (v.getId() == R.id.button_30_days){days = 30;}
        else if (v.getId() == R.id.button_90_days){days = 90;}
        else if (v.getId() == R.id.button_365_days){days = 365;}
        makeBarChart(days);
        makeLineChart(days);
    }

    /**
     * @param daysShown number of days which need to be shown on the chart
     */
    private void makeBarChart(int daysShown){

        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();// Initialize Array List
        ArrayList<Float> dbEntries = getMinutesOfActivityFromDB(daysShown); //List where the entries of the DB are put into

        // Chart Instances
        for (int i=0; i<daysShown; i++){
            float value = dbEntries.get(i);
            BarEntry barEntry = new BarEntry(i, value);// Initialize Entry
            barActivityEntries.add(barEntry);// Add Values in Array List
        }

        XAxis xAxis = barChartActivity.getXAxis();
        xAxis.setDrawGridLines(false);

        BarDataSet barDataSet = new BarDataSet(barActivityEntries, "Aktivität in Minuten");// Initialize Bar Data Set
        barDataSet.setColors(Color.parseColor("#3A2BA9"));// Set Bar Color
        barChartActivity.setData(new BarData(barDataSet));// Set Bar Data
        barChartActivity.animateY(3000);// Set Animations
        barChartActivity.getDescription().setText(" ");// Removing Description text
        //barChartActivity.getDescription().setTextColor(Color.WHITE);
        YAxis yAxisL = barChartActivity.getAxisLeft();
        YAxis yAxisR = barChartActivity.getAxisRight();
        yAxisL.setAxisMinimum(0);
        yAxisR.setAxisMinimum(0);
    }

    /**
     * @param daysToShow number of days which need to be shown on the chart
     * @return ArrayList<Float> with the minutes of activity for each day as floats
     */
    private ArrayList<Float> getMinutesOfActivityFromDB(int daysToShow){
        ArrayList<Float> dbEntries= new ArrayList<>();//List where the entries of the DB are put into
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        long timeStartOfTheDay = now.getTime() - (now.getTime() % millisecondsPerDay); //gets the time of the first millisecond of the current day

        //gets the DB entry for every day, starting with the day furthest in the past
        for(int i = daysToShow-1; i >= 0; i--){
            long neededDay = timeStartOfTheDay - (i * millisecondsPerDay);
            Long time = db.getActivityData(neededDay, neededDay + millisecondsPerDay); //get the DB entries for the needed day
            dbEntries.add(((float)time)/60000); // calculating milliseconds into minutes
        }

        return dbEntries;
    }

    /**
     * @param daysShown number of days which need to be shown on the chart
     */
    private void makeLineChart(int daysShown){

        ArrayList<ArrayList<Entry>> linesMoodEntries = new ArrayList<>(); // List of all Moods
        ArrayList<Entry> Mood1 = new ArrayList<>();
        ArrayList<Entry> Mood2 = new ArrayList<>();
        ArrayList<Entry> Mood3 = new ArrayList<>();
        ArrayList<Entry> Mood4 = new ArrayList<>();
        ArrayList<Entry> Mood5 = new ArrayList<>();
        ArrayList<Entry> Mood6 = new ArrayList<>();
        linesMoodEntries.add(Mood1);
        linesMoodEntries.add(Mood2);
        linesMoodEntries.add(Mood3);
        linesMoodEntries.add(Mood4);
        linesMoodEntries.add(Mood5);
        linesMoodEntries.add(Mood6);

        //Set Graphs Axis
        YAxis yAxisL = lineChartMood.getAxisLeft();
        YAxis yAxisR = lineChartMood.getAxisRight();
        yAxisL.setAxisMinimum(0);
        yAxisL.setAxisMaximum(100);
        yAxisR.setAxisMinimum(0);
        yAxisR.setAxisMaximum(100);
        XAxis xAxis = lineChartMood.getXAxis();
        xAxis.setDrawGridLines(false);

        //get database data
        ArrayList<ArrayList<Float>> dbEntries = getMoodScoresFromDB(daysShown);

        // Set chart values
        for(int i = 0; i < linesMoodEntries.size(); i++){
            ArrayList<Float> mood = dbEntries.get(i);
            for(int j = 0; j < daysShown; j++){
                float value = mood.get(j);
                if(value != 404){ // checking if there is valid data
                    Entry lineEntry = new Entry(j, value);// Initialize Entry
                    linesMoodEntries.get(i).add(lineEntry);// Add Values in Array List
                }
            }
        }

        //making the 6 different lines
        LineDataSet line1 = new LineDataSet(Mood1, "zufrieden");
        line1.setColor(Color.parseColor("#F94144"));
        line1.setCircleColor(Color.parseColor("#F94144"));
        line1.setDrawValues(false);
        line1.setLineWidth(2F);
        LineDataSet line2 = new LineDataSet(Mood2, "ruhe");
        line2.setColor(Color.parseColor("#F8961E"));
        line2.setCircleColor(Color.parseColor("#F8961E"));
        line2.setDrawValues(false);
        line2.setLineWidth(2F);
        LineDataSet line3 = new LineDataSet(Mood3, "wohl");
        line3.setColor(Color.parseColor("#F9C74F"));
        line3.setCircleColor(Color.parseColor("#F9C74F"));
        line3.setDrawValues(false);
        line3.setLineWidth(2F);
        LineDataSet line4 = new LineDataSet(Mood4, "entspannt");
        line4.setColor(Color.parseColor("#90BE6D"));
        line4.setCircleColor(Color.parseColor("#90BE6D"));
        line4.setDrawValues(false);
        line4.setLineWidth(2F);
        LineDataSet line5 = new LineDataSet(Mood5, "energie");
        line5.setColor(Color.parseColor("#43AA8B"));
        line5.setCircleColor(Color.parseColor("#43AA8B"));
        line5.setDrawValues(false);
        line5.setLineWidth(2F);
        LineDataSet line6 = new LineDataSet(Mood6, "wach");
        line6.setColor(Color.parseColor("#3D559F"));
        line6.setCircleColor(Color.parseColor("#3D559F"));
        line6.setDrawValues(false);
        line6.setLineWidth(2F);

        //adding all lines to the list: lineDataSetList
        ArrayList<ILineDataSet> lineDataSetList = new ArrayList<>(); // List of the sets
        lineDataSetList.add(line1);
        lineDataSetList.add(line2);
        lineDataSetList.add(line3);
        lineDataSetList.add(line4);
        lineDataSetList.add(line5);
        lineDataSetList.add(line6);

        //Inputting the line data into the graph
        LineData data = new LineData(lineDataSetList);
        lineChartMood.setData(data);
        lineChartMood.animateX(3000);
        lineChartMood.getDescription().setText(" ");

    }


    private ArrayList<ArrayList<Float>> getMoodScoresFromDB(int daysToShow){
        ArrayList<ArrayList<Float>> dbEntries= new ArrayList<>();//List where the entries of the DB are put into
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        long timeStartOfTheDay = now.getTime() - (now.getTime() % millisecondsPerDay); //gets the time of the first millisecond of the current day

        //gets the DB entry for every day, starting with the day furthest in the past
        for(int i = daysToShow-1; i >= 0; i--){
            long neededDay = timeStartOfTheDay - (i * millisecondsPerDay);
            ArrayList<Float> entriesOneDay = db.getMoodData(neededDay, neededDay + millisecondsPerDay); //get the DB entries for every mood for the needed day
            for(int p = 0; p < 6 ; i++){
                dbEntries.get(p).add(entriesOneDay.get(p));
            }
        }

        return  dbEntries;
    }


    /**
     * makes example entries in the DB for the charts
     */
    private void makeExampleDbEntries(int daysShown){
        Date now = new Date();
        long millisecondsPerDay = 86400000; // a day has 86400000 milliseconds
        db.insertActivity("Walking", now.getTime(), now.getTime() + 2700000);
        db.insertActivity("Walking", now.getTime()-(millisecondsPerDay*2), now.getTime()-(millisecondsPerDay*2) + 1800000);
        db.insertActivity("Walking", now.getTime()-(millisecondsPerDay*(daysShown-2)), now.getTime()-(millisecondsPerDay*(daysShown-2)) + 3600000);
    }

}