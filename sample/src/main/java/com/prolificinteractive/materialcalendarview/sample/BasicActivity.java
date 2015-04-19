package com.prolificinteractive.materialcalendarview.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Shows off the most basic usage
 */
public class BasicActivity extends ActionBarActivity implements OnDateChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private TextView textView;
    boolean debug = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        textView = (TextView) findViewById(R.id.textView);

        final MaterialCalendarView widget = (MaterialCalendarView) findViewById(R.id.calendarView);

        widget.setOnDateChangedListener(this);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();

        widget.setMinimumDate(today);
        widget.setMaximumDate(nextYear);
        widget.setSelectedDate(today);

        ((Button) findViewById(R.id.debugButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Calendar> palarcon = new ArrayList<Calendar>();
                List<Calendar> salonso = new ArrayList<Calendar>();

                for (int i=1; i<15; ++i) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(0);
                    cal.set(2015, Calendar.MAY, i);

                    palarcon.add(cal);
                }

                for (int i=16; i<23; ++i) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(0);
                    cal.set(2015, Calendar.MAY, i);

                    salonso.add(cal);
                }

                if(debug) {
                    widget.enableAllDays(false);

                    for (Calendar cal : palarcon) {
                        widget.enableDay(new CalendarDay(cal), true);
                    }
                } else {
                    widget.enableAllDays(false);

                    for (Calendar cal : salonso) {
                        widget.enableDay(new CalendarDay(cal), true);
                    }
                }
//                widget.enableDay(new CalendarDay(cal), debug);
//                widget.enableDayOfWeek(Calendar.MONDAY, debug);
//                widget.enableDayOfWeek(Calendar.WEDNESDAY, debug);

                debug = !debug;
            }
        });
    }

    @Override
    public void onDateChanged(MaterialCalendarView widget, CalendarDay date) {
        textView.setText(FORMATTER.format(date.getDate()));
    }
}
