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

public class StatisticsPageActivity extends AppCompatActivity implements View.OnClickListener{

    // Initialize Variables
    BarChart barChartActivity;
    LineChart lineChartMood;
    Button button7days;
    Button button30days;
    Button button90days;
    Button button365days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_page);

        // Assign Variables
        barChartActivity = findViewById(R.id.bar_chart_activity);
        lineChartMood = findViewById(R.id.line_chart_mood);
        button7days = (Button) findViewById(R.id.button_7_days);
        button30days = (Button) findViewById(R.id.button_30_days);
        button90days = (Button) findViewById(R.id.button_90_days);
        button365days = (Button) findViewById(R.id.button_365_days);
        button7days.setOnClickListener(this);
        button30days.setOnClickListener(this);
        button90days.setOnClickListener(this);
        button365days.setOnClickListener(this);

        makeBarChart(7);
        makeLineChart(7);

    }

    @Override
    public void onClick(View v)
    {
        int days = 7;
        if(v.getId() == R.id.button_7_days){days = 7;}
        else if (v.getId() == R.id.button_30_days){days = 30;}
        else if (v.getId() == R.id.button_90_days){days = 90;}
        else if (v.getId() == R.id.button_365_days){days = 365;}
        makeBarChart(days);
        makeLineChart(days);
    }

    private void makeBarChart(int daysShown){

        ArrayList<BarEntry> barActivityEntries = new ArrayList<>();// Initialize Array List

        // Example Instances
        for (int i=0; i<daysShown; i++){
            float value = (float) ((i+1)*10.0);// Convert To Float
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
    }

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

        // Example values
        for(int i = 0; i < linesMoodEntries.size(); i++){
            for(int j = 0; j < daysShown; j++){
                float value = (float) ((i+j)*8.0);// Convert To Float
                Entry lineEntry = new Entry(j, value);// Initialize Entry
                linesMoodEntries.get(i).add(lineEntry);// Add Values in Array List
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

}