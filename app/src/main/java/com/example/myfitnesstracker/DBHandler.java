package com.example.myfitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mikephil.charting.matrix.Vector3;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Tracker_Database";
    private static final int DB_V = 1;
    //define names for table Activity_Data
    private final String TABLE_SENSOR_DATA = "Sensor_Data";
    private final String KEY_SENSOR_R_TIME = "Record_Time";
    private final String KEY_SENSOR_XDATA = "X_Acceleration";
    private final String KEY_SENSOR_YDATA = "Y_Acceleration";
    private final String KEY_SENSOR_ZDATA = "Z_Acceleration";
    private final String KEY_SENSOR_SOURCE = "Data_Source";

    //define names for table Activity_Log
    private final String TABLE_ACTIVITY_LOG = "Activity_Log";
    private final String KEY_ACTIVITY_AID = "Activity_ID";
    private final String KEY_ACTIVITY_TYPE = "Activity_Type";
    private final String KEY_ACTIVITY_S_TIME = "Start_Time";
    private final String KEY_ACTIVITY_E_TIME = "End_Time";
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
        String CREATE_TABLE = "CREATE TABLE " + TABLE_SENSOR_DATA + "("
                + KEY_SENSOR_R_TIME + " REAL PRIMARY KEY, "
                + KEY_SENSOR_XDATA + " REAL, "
                + KEY_SENSOR_YDATA + " REAL, "
                + KEY_SENSOR_ZDATA + " REAL, "
                + KEY_SENSOR_SOURCE + " TEXT" + ")";
        db.execSQL((CREATE_TABLE));

        //create table activity log
        CREATE_TABLE = "CREATE TABLE " + TABLE_ACTIVITY_LOG + "("
                + KEY_ACTIVITY_AID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ACTIVITY_TYPE + " TEXT, "
                + KEY_ACTIVITY_S_TIME + " REAL, "
                + KEY_ACTIVITY_E_TIME + " REAL" + ")";
        db.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE " + TABLE_M_LOG + "()"
                + KEY_M_TIME + " REAL PRIMARY KEY, "
                + KEY_MOOD + " INT" + ")";
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //TODO: think about upgrade procedures and implement them
    }


    /**
     * @param data Sensor data as SensorData object
     * @param source name of the source device
     */
    public void safeSensorData(com.example.myfitnesstracker.model.SensorData data, String source){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_SENSOR_R_TIME, data.getTimestamp());
        cValues.put(KEY_SENSOR_XDATA, data.getX_data());
        cValues.put(KEY_SENSOR_YDATA, data.getY_data());
        cValues.put(KEY_SENSOR_ZDATA, data.getZ_data());
        cValues.put(KEY_SENSOR_SOURCE, source);
        db.insert(TABLE_SENSOR_DATA,null,cValues);
    }

    /**
     * @param timeData time the data is safed, used as primary key, must be unique in table
     * @param xData
     * @param yData
     * @param zData
     * @param source
     */
    public void safeSensorData(float timeData, float xData, float yData, float zData, String source){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_SENSOR_R_TIME, timeData);
        cValues.put(KEY_SENSOR_XDATA, xData);
        cValues.put(KEY_SENSOR_YDATA, yData);
        cValues.put(KEY_SENSOR_ZDATA, zData);
        cValues.put(KEY_SENSOR_SOURCE, source);
        db.insert(TABLE_SENSOR_DATA,null,cValues);
    }

    /**
     * @param aType type of the activity
     * @param sTime start time of the activity
     * @return rowid of the inserted activity or -1 if insertion failed
     */
    public long insertActivityStart(String aType, long sTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ACTIVITY_TYPE, aType);
        cValues.put(KEY_ACTIVITY_S_TIME, sTime);
        return db.insert(TABLE_ACTIVITY_LOG,null,cValues);
    }

    /**
     * @param aType type of the activity
     * @param sTime start time of the activity
     * @param eTime end time of the activity
     * @return rowid of the inserted activity or -1 if insertion failed
     */
    public long insertActivity(String aType, long sTime, long eTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ACTIVITY_TYPE, aType);
        cValues.put(KEY_ACTIVITY_S_TIME, sTime);
        cValues.put(KEY_ACTIVITY_E_TIME, eTime);
        return db.insert(TABLE_ACTIVITY_LOG,null,cValues);
    }

    /**
     * @param rowID id of the row to update
     * @param eTime end time to be inserted
     * @return returns 1 if update worked, other value indicates something went wrong
     */
    public int insertActivityEnd(int rowID, long eTime){
        ContentValues cv = new ContentValues();
        cv.put(KEY_ACTIVITY_E_TIME,eTime);
        SQLiteDatabase db = this.getReadableDatabase();
        return db.update(TABLE_ACTIVITY_LOG,cv,"rowid = ?",new String[]{Integer.toString(rowID)});
    }
    /**
     * returns an arraylist of only the data value from the data table or null if the table is empty
     * obsolete, please use getSensorData(float minTime)
     */
    public ArrayList<com.github.mikephil.charting.matrix.Vector3> getSensorData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SENSOR_DATA, new String[]{KEY_SENSOR_XDATA, KEY_SENSOR_YDATA,KEY_SENSOR_ZDATA},null,null,null,null,null);
        if (!cursor.moveToFirst()) return null;
        ArrayList<com.github.mikephil.charting.matrix.Vector3> out = new ArrayList<>(cursor.getCount());
        while (!cursor.isAfterLast()) {
            out.add(new com.github.mikephil.charting.matrix.Vector3(cursor.getFloat(0),cursor.getFloat(1),cursor.getFloat(2)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return out;
    }
    /**
     *
     * @param minTime minimum Time to consider
     * @return ArrayList<ContentValues> with "time" and "value" values or null is table is empty
     */
    public ArrayList<ContentValues> getSensorData(float minTime){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SENSOR_DATA, new String[]{KEY_SENSOR_R_TIME, KEY_SENSOR_XDATA, KEY_SENSOR_YDATA,KEY_SENSOR_ZDATA},"? >= ?",new String[]{KEY_SENSOR_R_TIME, Float.toString(minTime)},null,null,null);
        if (!cursor.moveToFirst()) return null;
        ArrayList<ContentValues> out = new ArrayList<>(cursor.getCount());
        while (!cursor.isAfterLast()) {
            ContentValues cv = new ContentValues();
            Vector3 vector = new Vector3(cursor.getFloat(1),cursor.getFloat(2),cursor.getFloat(3));
            cv.put("time", cursor.getFloat(0));
            cv.put("value",vector.length());
            out.add(cv);
            cursor.moveToNext();
        }
        cursor.close();
        return out;
    }


}
