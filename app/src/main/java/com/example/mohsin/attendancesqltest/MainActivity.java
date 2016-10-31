package com.example.mohsin.attendancesqltest;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Subject ma101,ap101,ee101,co101,me105,en101,apLab,coLab,eeLab, lunch;
    Timetable timetable;
    AttendanceDBHelper attendanceDBHelper;

    ListView listView;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        listView = (ListView) findViewById(R.id.subjectList);

        initSubjects();
        initTimeTable();
    }

    protected void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tan","Resumed Main Activity");
        populateList();
    }

    private void initTimeTable() {
        Subject[] subjectList = {ma101,ap101,ee101,co101,me105,en101,apLab,coLab,eeLab, lunch};
        timetable = new Timetable(subjectList);

        attendanceDBHelper = new AttendanceDBHelper(this,subjectList);
        //attendanceDBHelper.deleteTable();

        attendanceDBHelper.getData();

    }

    //TODO: Remove Hardcoded Data from here
    public void initSubjects(){
        ma101 = new Subject("MA101","Mathematics - I",0,0,attendanceDBHelper);
        ap101 = new Subject("AP101","PHYSICS-I",0,0,attendanceDBHelper);
        ee101 = new Subject("EE101","BASIC ELECTRICAL ENGINEERING",0,0,attendanceDBHelper);
        co101 = new Subject("CO101","PROGRAMMING FUNDAMENTALS",0,0,attendanceDBHelper);
        me105 = new Subject("ME10", "ENGINEERING GRAPHICS",0,0,attendanceDBHelper);
        en101 = new Subject("EN101", "INTRODUCTION TO ENVIRONMENTAL SCIENCE",0,0,attendanceDBHelper);

        apLab = new Subject("AP101(LAB)","AP Lab",0,0,attendanceDBHelper);
        coLab = new Subject("CO101(LAB)","CO Lab",0,0,attendanceDBHelper);
        eeLab = new Subject("EE101(LAB)","EE Lab",0,0,attendanceDBHelper);

        lunch = new Subject("Lunch","Lunch",0,0,attendanceDBHelper);

        int[] maTimeBracket = {10,16,17,30};
        int[] apTimeBracket = {11,31,34};
        int[] eeTimeBracket = {1,18,37};
        int[] coTimeBracket = {2,15,38};
        int[] enTimeBracket = {3,14,35};

        int[] meTB = {21,22,23};
        int[] aplabTB = {26,27};
        int[] coLabTB = {6,7};
        int[] eeLabTB = {32,33};

        int[] breakTB = {4,12,20,28,36};

        ma101.setTimeBracket(convertToArrayList(maTimeBracket));
        ap101.setTimeBracket(convertToArrayList(apTimeBracket));
        ee101.setTimeBracket(convertToArrayList(eeTimeBracket));
        co101.setTimeBracket(convertToArrayList(coTimeBracket));
        en101.setTimeBracket(convertToArrayList(enTimeBracket));

        me105.setTimeBracket(convertToArrayList(meTB));
        apLab.setTimeBracket(convertToArrayList(aplabTB));
        coLab.setTimeBracket(convertToArrayList(coLabTB));
        eeLab.setTimeBracket(convertToArrayList(eeLabTB));

        lunch.setTimeBracket(convertToArrayList(breakTB));
    }

    public ArrayList<Integer> convertToArrayList(int[] ints){
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < ints.length; index++)
        {
            intList.add(ints[index]);
        }

        return intList;

    }

    public void populateList(){
        final Cursor cursor = attendanceDBHelper.getData();
        SubjectListAdapter subjectListAdapter = new SubjectListAdapter(MainActivity.this,cursor,0);
        listView.setAdapter(subjectListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getApplicationContext(),SubjectActivity.class);

                Cursor cursor1 = attendanceDBHelper.getDataFromId(position+1);
                DatabaseUtils.dumpCursorToString(cursor1);
                cursor1.moveToFirst();

                int attended = Integer.parseInt( cursor1.getString(cursor1.getColumnIndex("Attended")) );
                int total    = Integer.parseInt( cursor1.getString(cursor1.getColumnIndex("Total")) );
                String code = cursor1.getString(cursor1.getColumnIndex("Code"));

                int percentage = 0;

                if (total !=0 ){
                    percentage = (attended * 100)/total;
                }
                intent.putExtra("CODE",code);
                intent.putExtra("PERCENTAGE",percentage);
                intent.putExtra("CLASSES",attended+"/"+total);

                intent.putExtra("ATTENDED",attended);
                intent.putExtra("TOTAL",total);

                startActivity(intent);

            }
        });
    }

}
