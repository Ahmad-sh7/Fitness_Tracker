package com.example.myfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class StatisticsPageActivity extends AppCompatActivity {

    // Initialize Variables
    BarChart barChartActivity;
    BarChart barChartMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        // Assign Variables
        barChartActivity = findViewById(R.id.bar_chart_activity);
        barChartMood = findViewById(R.id.bar_chart_mood);

        // Initialize Array List
        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();

        // Example Instances
        for (int i=1; i<10; i++){

            // Convert To Float
            float value = (float) (i*10.0);

            // Initialize Bar and Pie Chart Entry
            BarEntry barEntry = new BarEntry(i, value);

            // Add Values in Array List
            barActivityEntries.add(barEntry);

        }

        // Initialize Bar Data Set
        BarDataSet barDataSet = new BarDataSet(barActivityEntries, "Test");

        // Set Colors
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        // Hide draw values
        barDataSet.setDrawValues(false);

        // Set Bar Data
        barChartActivity.setData(new BarData(barDataSet));

        // Set Animations
        barChartActivity.animateY(5000);

        // Set Description text and color
        barChartActivity.getDescription().setText("Test chart");
        barChartActivity.getDescription().setTextColor(Color.BLUE);

    }
}