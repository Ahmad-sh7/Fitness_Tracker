package com.example.myfitnesstracker.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SensorDataDao {
    @Query("SELECT * FROM sensorData")
    List<SensorData> getAll();

    @Insert
    void insertAll(SensorData...sensorData);






}
