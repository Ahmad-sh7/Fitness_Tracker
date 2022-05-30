package com.example.myfitnesstracker.model;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.myfitnesstracker.model.typeconverter.SensorDataListTypeConverter;

@Database(entities = {ActivityRecord.class},version = 1)
@TypeConverters(SensorDataListTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SensorDataDao sensorDataDao();
}
