package com.example.mohsin.attendancesqltest;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mohsin on 20/08/16.
 */
public class Timetable {

    String[][] timetableString;

    public Timetable(Subject[] subjectList) {

        timetableString = new String[5][8];

        for (int i = 0 ;i<subjectList.length;i++){
            for (int j = 0;j<subjectList[i].getTotalClasses();j++ ){
                int day = subjectList[i].getDayCode(j);
                int time = subjectList[i].getTimeCode(j);

                timetableString[day][time] = subjectList[i].getCode();
            }
        }

        for (int i =0 ;i<5;i++){
            for (int j = 0 ;j<8;j++){
                if (timetableString[i][j]==null){
                    timetableString[i][j] = "X";
                }
            }
            Log.d("TimeTable: " , Arrays.toString(timetableString[i]));
        }
    }

    public String[] getTimeTableOfDayWithIndex(int index){
        return timetableString[index];
    }


}
