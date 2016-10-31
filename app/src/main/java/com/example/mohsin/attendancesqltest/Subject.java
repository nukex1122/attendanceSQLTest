package com.example.mohsin.attendancesqltest;

import java.util.ArrayList;

/**
 * Created by mohsin on 20/08/16.
 */
public class Subject {
    private String code;
    private String Name;
    private ArrayList<Integer> timeBracket;
    private int noOfClassesAttended;
    private int totalClassTillNow;

    AttendanceDBHelper attendanceDBHelper;

    public Subject(String code, String name, int noOfClassesAttended, int totalClassTillNow, AttendanceDBHelper attendanceDBHelper) {
        this.code = code;
        Name = name;
        this.noOfClassesAttended = noOfClassesAttended;
        this.totalClassTillNow = totalClassTillNow;

        this.attendanceDBHelper = attendanceDBHelper;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTimeBracket(ArrayList<Integer> timeBracket){
        this.timeBracket = timeBracket;
    }

    public int getNoOfClassesAttended() {

        attendanceDBHelper.getNoOfAttendedClasses(code);

        return noOfClassesAttended;
    }

    public void setNoOfClassesAttended(int noOfClassesAttended) {
        this.noOfClassesAttended = noOfClassesAttended;
    }

    public int getTotalClassTillNow() {
        return totalClassTillNow;
    }

    public void setTotalClassTillNow(int totalClassTillNow) {
        this.totalClassTillNow = totalClassTillNow;
    }

    public int getDayCode(int index){
        return (timeBracket.get(index)/8);
    }

    public int getTimeCode(int index){
        return timeBracket.get(index)%8;
    }

    public int getTotalClasses(){
        return timeBracket.size();
    }
}
