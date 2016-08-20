package com.example.mohsin.attendancesqltest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mohsin on 20/08/16.
 */
public class TimetableDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Timetable.db";

    public TimetableDBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TIMETABLE " +
                    "(Day text primary key, 9to10 integer," +
                    " 9to10 integer," +
                    " 10to11 integer," +
                    " 11to12 integer," +
                    " 12to1 integer," +
                    " 1to2 integer," +
                    " 2to3 integer," +
                    " 3to4 integer," +
                    " 4to5 integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void insertSubject(Subject subject){

    }
}
