package com.example.mohsin.attendancesqltest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubjectActivity extends AppCompatActivity {

    TextView percentageView, classesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        percentageView = (TextView) findViewById(R.id.percentageDetailed);
        classesView = (TextView) findViewById(R.id.classDetailed);

        Intent intent = getIntent();

        Bundle bd = intent.getExtras();

        if (bd!= null){
            String percentage = (String) bd.get("PERCENTAGE");
            String classes = (String) bd.get("CLASSES");

            percentageView.setText(percentage);
            classesView.setText(classes);
        }
    }
}
