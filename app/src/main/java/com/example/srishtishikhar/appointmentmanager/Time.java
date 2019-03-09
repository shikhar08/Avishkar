package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Time extends AppCompatActivity {

    TimePicker timePicker1;
    int hour, min;
    String format;
    String time;
    Button b1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        b1 = (Button) findViewById(R.id.button6);
        hour = timePicker1.getCurrentHour();
        min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, final int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }


        final int finalHour = hour;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("hour", String.valueOf(finalHour));
                b.putString("minute", String.valueOf(min));
                b.putString("format", format);
                Intent i = new Intent(Time.this, NewDoctor.class);
       i.putExtras(b);

                startActivity(i);
                finish();
            }
        });
    }
}
