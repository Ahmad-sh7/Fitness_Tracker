package com.example.myfitnesstracker.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SensorDataDao {
    @Query("SELECT * FROM activityrecord")
    List<ActivtyRecord> getAll();

    @Insert
    void insertAll(ActivtyRecord...activtyRecords);


}
