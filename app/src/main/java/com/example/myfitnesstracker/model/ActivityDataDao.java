package com.example.myfitnesstracker.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ActivityDataDao {

    @Query("SELECT * FROM activity_log")
    List<Activity_log> getAll();

    @Insert
    void insertAll(Activity_log...activity_log);
}
