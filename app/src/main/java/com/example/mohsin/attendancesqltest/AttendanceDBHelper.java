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

    public AttendanceDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (" +
                        "_id integer primary key autoincrement," +
                        "Code text," +
                        "Attended int," +
                        "Total int" +
                    ")");

        Log.d("SQL","Created");

        insertSubject();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS attendance");
        onCreate(db);
    }

    public boolean insertSubject(){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0;i<subjects.length;i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put("Code",subjects[i].getCode());
            contentValues.put("Attended", subjects[i].getNoOfClassesAttended());
            contentValues.put("Total", subjects[i].getTotalClassTillNow());
            db.insert(TABLE_NAME, null, contentValues);
        }

        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);

        Log.d("Attendance", DatabaseUtils.dumpCursorToString(res));
        return res;
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("Drop","Dropped");
        db.execSQL("DROP TABLE IF EXISTS attendance");
        Log.d("Made","Made");
        onCreate(db);

    }

    public void incrementAttendedClasses (String code) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = getDataFromCode(code);
        data.moveToFirst();

        String attended = data.getString(data.getColumnIndex("Attended"));
        String Total = data.getString(data.getColumnIndex("Total"));

        ContentValues contentValues = new ContentValues();

        contentValues.put("Code",code);
        contentValues.put("Attended", Integer.parseInt(attended) +1);
        contentValues.put("Total", Integer.parseInt(Total));

        db.update(TABLE_NAME, contentValues, "Code = ? ", new String[]{code});
    }

    public void incrementTotalClasses (String code) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = getDataFromCode(code);
        data.moveToFirst();

        String attended = data.getString(data.getColumnIndex("Attended"));
        String Total = data.getString(data.getColumnIndex("Total"));

        ContentValues contentValues = new ContentValues();

        contentValues.put("Code",code);
        contentValues.put("Attended", Integer.parseInt(attended));
        contentValues.put("Total", Integer.parseInt(Total) + 1);

        db.update(TABLE_NAME, contentValues, "Code = ? ", new String[]{code});
    }

    public void getNoOfAttendedClasses(String code){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select Attended from " + TABLE_NAME + " where Code is \"" + code + "\"",null );

        Log.d(TABLE_NAME,DatabaseUtils.dumpCursorToString(res));
    }

    public Cursor getDataFromCode(String Code){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where Code is \"" + Code + "\"",null);
        return res;
    }

    public Cursor getDataFromId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where _id is \"" + id + "\"",null);
        return res;
    }
}
