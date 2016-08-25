package com.example.mohsin.attendancesqltest;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by mohsin on 24/08/16.
 */
public class SubjectListAdapter extends CursorAdapter {

    private LayoutInflater cursorInflater;

    //AttendanceDBHelper attendanceDBHelper;
    Cursor cursor;

    public SubjectListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  this.attendanceDBHelper = attendanceDBHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        String[] colorCodes = {"#00838F","#0288D1","#E74C3C"};

        view.setBackgroundColor(Color.parseColor(colorCodes[position%3]));
        return view;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return cursorInflater.inflate(R.layout.attendance_list_layout2,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById(R.id.subject_code2);

        String attended = cursor.getString(cursor.getColumnIndex("Code"));

        textView.setText(attended);
    }


}
