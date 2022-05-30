package com.example.myfitnesstracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class SensorData {
    long timestamp;
    double x_data;
    double y_data;
    double z_data;


    public SensorData(long timestamp, double x_data, double y_data, double z_data) {
        this.timestamp = timestamp;
        this.x_data = x_data;
        this.y_data = y_data;
        this.z_data = z_data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getX_data() {
        return x_data;
    }

    public void setX_data(double x_data) {
        this.x_data = x_data;
    }

    public double getY_data() {
        return y_data;
    }

    public void setY_data(double y_data) {
        this.y_data = y_data;
    }

    public double getZ_data() {
        return z_data;
    }

    public void setZ_data(double z_data) {
        this.z_data = z_data;
    }
}
