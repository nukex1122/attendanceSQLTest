package com.example.mohsin.attendancesqltest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SubjectActivity extends AppCompatActivity {

    TextView percentageView, classesView, bunkableClass;
    Button datePickerButton, resetButton;
    DatePickerDialog datePicker;
    AttendanceDBHelper attendanceDBHelper;
    DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM, yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        attendanceDBHelper = new AttendanceDBHelper(this);

        percentageView = (TextView) findViewById(R.id.percentageDetailed);
        classesView = (TextView) findViewById(R.id.classDetailed);
        datePickerButton = (Button) findViewById(R.id.datePicker);
        bunkableClass = (TextView) findViewById(R.id.bunkableClass);
        resetButton = (Button) findViewById(R.id.resetButton);

        Intent intent = getIntent();

        final Bundle bd = intent.getExtras();

        if (bd!= null){
            int percentage = (int) bd.get("PERCENTAGE");
            String classes = (String) bd.get("CLASSES");

            int attended = (int)bd.get("ATTENDED");
            int total = (int)bd.get("TOTAL");

            percentageView.setText(percentage + "%");
            classesView.setText(classes);

            datePickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SubjectActivity.this);
                    alertDialog.setTitle("Did You Attend The Class");
                    alertDialog.setMessage("Select tp of class");

                    final EditText input = new EditText(SubjectActivity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialog.setView(input);

                    alertDialog.setPositiveButton("YES",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    attendanceDBHelper.incrementAttendedClasses((String) bd.get(("CODE")));
                                    attendanceDBHelper.incrementTotalClasses((String) bd.get("CODE"));

                                }
                            });

                    alertDialog.setNegativeButton("NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    attendanceDBHelper.incrementTotalClasses((String) bd.get("CODE"));
                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();
                }

            });

            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attendanceDBHelper.clearAttendanceOfSubjectWithCode((String) bd.get("CODE"));
                    percentageView.setText("0%");
                    bunkableClass.setText("Start Attending Some Classes");
                    classesView.setText("0/0");
                }
            });

            int x;
            if (percentage<75){
                x = (3*total) - (4 * attended);
                bunkableClass.setText("You Have To Attend " + x + " More Classes");
            }else if(percentage == 75){
                bunkableClass.setText("You Cannot Bunk More classes");
            }else if(percentage > 75){
                x = (int) ( (1.3 * attended)- total);
                bunkableClass.setText("You Can Bunk " + x + " More Classes");
            }
        }

        attendanceDBHelper.getData();
    }
}
