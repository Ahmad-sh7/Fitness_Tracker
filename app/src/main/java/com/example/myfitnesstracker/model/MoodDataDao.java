package com.example.myfitnesstracker.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoodDataDao {
    @Query("SELECT * FROM mooddata")
    List<MoodData> getAll();

    @Insert
    void insertAll(MoodData...moodData);
}
