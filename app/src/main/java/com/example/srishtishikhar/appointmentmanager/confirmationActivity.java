package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.os.*;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class confirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Patient");
        String id = bundle.getString("id");
        String time = bundle.getString("time");
        TextView tv1 = (TextView) findViewById(R.id.greet);
        tv1.setText("Dear "+name+"\n"+" Your Appointment ID is "+ id);

        TextView tv2 = (TextView) findViewById(R.id.timegiven);
        tv2.setText("You must reach to the clinic at "+time);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent = new Intent(confirmationActivity.this,LoginasPatient.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

}
