package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
//import com.github.mikephil.charting.data.LineEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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

    }


    private void makeBarChart(){

        // Initialize Array List
        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();

        // Example Instances
        for (int i=1; i<=7; i++){

            // Convert To Float
            float value = (float) (i*10.0);

            // Initialize Bar and Pie Chart Entry
            BarEntry barEntry = new BarEntry(i, value);

            // Add Values in Array List
            barActivityEntries.add(barEntry);
        }

        // Initialize Bar Data Set
        BarDataSet barDataSet = new BarDataSet(barActivityEntries, "Daily Activity in minutes");

        // Set Colors
        barDataSet.setColors(Color.rgb(55,00,179));

        // Hide draw values
        barDataSet.setDrawValues(false);

        // Set Bar Data
        barChartActivity.setData(new BarData(barDataSet));

        // Set Animations
        barChartActivity.animateY(5000);

        // Removing Description text
        barChartActivity.getDescription().setText(" ");
        //barChartActivity.getDescription().setTextColor(Color.WHITE);
    }

}