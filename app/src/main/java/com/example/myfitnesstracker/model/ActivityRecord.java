package com.example.myfitnesstracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "activityrecord")
public class ActivtyRecord {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public List<SensorData> sensorDataList;

    @ColumnInfo
    public String activityType;

    @ColumnInfo
    public long startTime;

    @ColumnInfo
    public long endTime;

    public ActivtyRecord( List<SensorData> sensorDataList, String activityType, long startTime, long endTime) {
        this.sensorDataList = sensorDataList;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
