package com.baway.historydays.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baway.historydays.R;
import com.dsw.calendar.component.MonthView;
import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.views.CircleCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CircleCalendarActivity extends Activity {
    private CircleCalendarView circleCalendarView;
    private int y;
    private int m;
    private int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_calendar_view);
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
        circleCalendarView = (CircleCalendarView) findViewById(R.id.circleMonthView);
        circleCalendarView.setCalendarInfos(list);
        circleCalendarView.setDateClick(new MonthView.IDateClick(){

            @Override
            public void onClickOnDate(int year, int month, int day) {
                y = year;
                m = month;
                d = day;

                Toast.makeText(CircleCalendarActivity.this,"点击了" +  year + "-" + month + "-" + day,Toast.LENGTH_SHORT).show();
            }
        });
        Button rili = (Button) findViewById(R.id.rili);
        rili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CircleCalendarActivity.this,MainActivity.class);
                intent.putExtra("year",y);
                intent.putExtra("month",m);
                intent.putExtra("day",d);
                startActivity(intent);
            }
        });

    }
}
