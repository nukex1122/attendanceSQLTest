package com.example.mohsin.attendancesqltest;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Subject ma101,ap101,ee101,co101,me105,en101,apLab,coLab,eeLab, lunch;
    Timetable timetable;
    //AttendanceDBHelper attendanceDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSubjects();
        initTimeTable();
    }

    private void initTimeTable() {
        Subject[] subjectList = {ma101,ap101,ee101,co101,me105,en101,apLab,coLab,eeLab, lunch};
        timetable = new Timetable(subjectList);

        //attendanceDBHelper = new AttendanceDBHelper(this,subjectList);
        //attendanceDBHelper.insertSubject();

        //attendanceDBHelper.getData();
    }

    //TODO: Remove Hardcoded Data from here
    public void initSubjects(){
        ma101 = new Subject("MA101","Mathematics - I",0,0);
        ap101 = new Subject("AP101","PHYSICS-I",0,0);
        ee101 = new Subject("EE101","BASIC ELECTRICAL ENGINEERING",0,0);
        co101 = new Subject("CO101","PROGRAMMING FUNDAMENTALS",0,0);
        me105 = new Subject("ME10", "ENGINEERING GRAPHICS",0,0);
        en101 = new Subject("EN101", "INTRODUCTION TO ENVIRONMENTAL SCIENCE",0,0);

        apLab = new Subject("AP101(LAB)","AP Lab",0,0);
        coLab = new Subject("CO101(LAB)","CO Lab",0,0);
        eeLab = new Subject("EE101(LAB)","EE Lab",0,0);

        lunch = new Subject("Lunch","Lunch",0,0);

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

}
