package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.data.LineEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.*;
import java.util.ArrayList;

public class StatisticsPageActivity extends AppCompatActivity {

    // Initialize Variables
    BarChart barChartActivity;
    LineChart lineChartMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        // Assign Variables
        barChartActivity = findViewById(R.id.bar_chart_activity);
        lineChartMood = findViewById(R.id.line_chart_mood);

        makeBarChart();
        makeLineChart();

    }


    private void makeBarChart(){

        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();// Initialize Array List

        // Example Instances
        for (int i=1; i<=7; i++){
            float value = (float) ((i+1)*10.0);// Convert To Float
            BarEntry barEntry = new BarEntry(i, value);// Initialize Bar and Pie Chart Entry
            barActivityEntries.add(barEntry);// Add Values in Array List
        }

        BarDataSet barDataSet = new BarDataSet(barActivityEntries, "Daily Activity in minutes");// Initialize Bar Data Set
        barDataSet.setColors(Color.rgb(55,0,179));// Set Bar Color
        barChartActivity.setData(new BarData(barDataSet));// Set Bar Data
        barChartActivity.animateY(5000);// Set Animations
        barChartActivity.getDescription().setText(" ");// Removing Description text
        //barChartActivity.getDescription().setTextColor(Color.WHITE);
    }

    private void makeLineChart(){

        ArrayList<ArrayList<Entry>> linesMoodEntries = new ArrayList<ArrayList<Entry>>(); // List of all Moods
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

    }

}