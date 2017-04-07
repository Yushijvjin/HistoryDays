package com.baway.historydays.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baway.historydays.R;
import com.baway.historydays.calendar.APPConfig;
import com.baway.historydays.calendar.MyCalendar;


public class CalendarActivity extends AppCompatActivity {

    MyCalendar myCalendar;
    EditText editTextYear;
    EditText editTextMonth;
    private Button btnconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        getSupportActionBar().hide();

        myCalendar = (MyCalendar) findViewById(R.id.mycalendar);
        myCalendar.setOnCalendarClickListener(new MyCalendar.OnCalendarClickListener() {

            @Override
            public void onCalendaeClick(int dateNum, APPConfig.CalendarState calendarState) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplication(), "dateNum:" + dateNum + "  calendarState:" + calendarState, Toast.LENGTH_LONG).show();

//                int year = DateUtil.getYear();
//
//                int month = DateUtil.getMonth();
//
//                Toast.makeText(getApplication(), "year" + DateUtil.getYear() + "month" + DateUtil.getMonth() + "date" + DateUtil.getCurrentMonthDay(), Toast.LENGTH_LONG).show();

            }
        });

        editTextYear = (EditText) findViewById(R.id.edityear);
        editTextMonth = (EditText) findViewById(R.id.editmonth);
        btnconfirm = (Button) findViewById(R.id.btnconfirm);

        btnconfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                myCalendar.setYearMonth(Integer.parseInt(editTextYear.getText().toString()), Integer.parseInt(editTextMonth.getText().toString()));
            }
        });


    }
}
