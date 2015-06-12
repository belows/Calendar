package com.belows.calendar;

import android.app.Activity;
import android.os.Bundle;

import com.belows.calendar.util.ChineseCalendar;
import com.belows.calendar.widget.CalendarCell;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarCell cc = (CalendarCell) findViewById(R.id.cc_test);
        cc.updateView(new ChineseCalendar(), "", true, false);
    }
}
