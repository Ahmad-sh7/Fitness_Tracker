package com.example.myfitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Tracker_Database";
    private static final int DB_V = 1;
    //define names for table Activity_Data
    private final String TABLE_A_DATA = "Activity_Data";
    private final String KEY_S_TIME = "Start_Time";
    private final String KEY_DATA = "Device_Data";
    private final String KEY_SOURCE = "Data_Source";

    //define names for table Activity_Log
    private final String TABLE_A_LOG = "Activity_Log";
    private final String KEY_AID = "Activity_ID";
    private final String KEY_A_TYPE = "Activity_Type";
    private final String KEY_E_TIME = "End_Time";
    // Start_Time defined above

    //define names for table Mood_Log
    private final String TABLE_M_LOG = "Mood_Log";
    private final String KEY_M_TIME = "Time";
    private final String KEY_MOOD = "Mood";

    //define names for table Questionnaires
    private final String TABLE_Quest = "Questionnaires";
    private final String KEY_version = "Version";


    DBHandler(Context context){
        super(context, DB_NAME, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Create table activity data table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_A_DATA + "." + TABLE_A_DATA + "("
                + KEY_S_TIME + "REAL PRIMARY KEY, "
                + KEY_DATA + " REAL, "
                + KEY_SOURCE + " TEXT" + ")";
        db.execSQL((CREATE_TABLE));

        //create table activity log
        CREATE_TABLE = "CREATE TABLE " + TABLE_A_LOG + "." + TABLE_A_LOG + "("
                + KEY_AID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_A_TYPE + " TEXT, "
                + KEY_S_TIME + " REAL, "
                + KEY_E_TIME + " REAL" + ")";
        db.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE" + TABLE_M_LOG + "." + TABLE_M_LOG + "()"
                + KEY_M_TIME + " REAL PRIMARY KEY, "
                + KEY_MOOD + " INT" + ")";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO: think about upgrade procedures and implement them
    }

    public void safeActivityData(long sTime, long data, String source){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_S_TIME, sTime);
        cValues.put(KEY_DATA, data);
        cValues.put(KEY_SOURCE, source);
        db.insert(TABLE_A_DATA,null,cValues);
    }
    public void insertActivityStart(String aType, long sTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_A_TYPE, aType);
        cValues.put(KEY_S_TIME, sTime);
        long tableIndex = db.insert(TABLE_A_LOG,null,cValues);
    }
    public void insertActivityStart(String aType, long sTime, long eTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_A_TYPE, aType);
        cValues.put(KEY_S_TIME, sTime);
        cValues.put(KEY_E_TIME, eTime);
        long tableIndex = db.insert(TABLE_A_LOG,null,cValues);
    }
    //TODO: implement entering activity end
/*    public void insertActivityEnd(long eTime){

    }*/





}
