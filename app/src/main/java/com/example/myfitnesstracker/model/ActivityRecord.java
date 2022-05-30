package com.example.myfitnesstracker.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
@Entity(tableName = "activityrecord")
public class ActivityRecord{
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

    public ActivityRecord(List<SensorData> sensorDataList, String activityType, long startTime, long endTime) {
        this.sensorDataList = sensorDataList;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
