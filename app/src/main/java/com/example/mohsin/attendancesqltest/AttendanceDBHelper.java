package com.example.mohsin.attendancesqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mohsin on 22/08/16.
 */
public class AttendanceDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Attendance.db";
    public final String TABLE_NAME = "attendance";

    public Subject[] subjects;
    public AttendanceDBHelper(Context context,Subject[] subjects) {
        super(context, DATABASE_NAME , null, 1);
        this.subjects = subjects;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (" +
                        "Code text primary key," +
                        "Attended int," +
                        "Total int" +
                    ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertSubject(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        for (int i = 0;i<subjects.length;i++){
            contentValues.put("Code",subjects[i].getCode());
            contentValues.put("Attended", subjects[i].getNoOfClassesAttended());
            contentValues.put("Total", subjects[i].getTotalClassTillNow());
        }

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public void getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where Code = MA101",null);

        Log.d("Attendance", DatabaseUtils.dumpCursorToString(res));
    }
}
