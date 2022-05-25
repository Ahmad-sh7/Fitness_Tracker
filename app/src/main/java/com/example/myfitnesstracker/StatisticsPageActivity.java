package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.data.LineEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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
            BarEntry barEntry = new BarEntry(i, value);// Initialize Entry
            barActivityEntries.add(barEntry);// Add Values in Array List
        }

        BarDataSet barDataSet = new BarDataSet(barActivityEntries, "Activität in Minuten");// Initialize Bar Data Set
        barDataSet.setColors(Color.parseColor("#3A2BA9"));// Set Bar Color
        barChartActivity.setData(new BarData(barDataSet));// Set Bar Data
        barChartActivity.animateY(3000);// Set Animations
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

        //Set Graphs y-Axis
        YAxis yAxis = lineChartMood.getAxisLeft();
        yAxis.setAxisMinimum(0);
        //yAxis.setAxisMaximum(100);

        // Example values
        for(int i = 0; i < linesMoodEntries.size(); i++){
            for(int j = 1; j <= 7; j++){
                float value = (float) ((i+j)*10.0);// Convert To Float
                Entry lineEntry = new Entry(j, value);// Initialize Entry
                linesMoodEntries.get(i).add(lineEntry);// Add Values in Array List
            }
        }

        ArrayList<ILineDataSet> lineDataSetList = new ArrayList<>(); // List of the sets
        LineDataSet line1 = new LineDataSet(Mood1, "zufrieden");
        line1.setColor(Color.parseColor("#F94144"));
        line1.setCircleColor(Color.parseColor("#F94144"));
        line1.setLineWidth(2F);
        LineDataSet line2 = new LineDataSet(Mood2, "ruhe");
        line2.setColor(Color.parseColor("#F8961E"));
        line2.setCircleColor(Color.parseColor("#F8961E"));
        line2.setLineWidth(2F);
        LineDataSet line3 = new LineDataSet(Mood3, "wohl");
        line3.setColor(Color.parseColor("#F9C74F"));
        line3.setCircleColor(Color.parseColor("#F9C74F"));
        line3.setLineWidth(2F);
        LineDataSet line4 = new LineDataSet(Mood4, "entspannt");
        line4.setColor(Color.parseColor("#90BE6D"));
        line4.setCircleColor(Color.parseColor("#90BE6D"));
        line4.setLineWidth(2F);
        LineDataSet line5 = new LineDataSet(Mood5, "energie");
        line5.setColor(Color.parseColor("#43AA8B"));
        line5.setCircleColor(Color.parseColor("#43AA8B"));
        line5.setLineWidth(2F);
        LineDataSet line6 = new LineDataSet(Mood6, "wach");
        line6.setColor(Color.parseColor("#3D559F"));
        line6.setCircleColor(Color.parseColor("#3D559F"));
        line6.setLineWidth(2F);
        lineDataSetList.add(line1);
        lineDataSetList.add(line2);
        lineDataSetList.add(line3);
        lineDataSetList.add(line4);
        lineDataSetList.add(line5);
        lineDataSetList.add(line6);

        LineData data = new LineData(lineDataSetList);
        lineChartMood.setData(data);
        lineChartMood.animateX(3000);
        lineChartMood.getDescription().setText(" ");

    }

}