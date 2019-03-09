package com.example.srishtishikhar.appointmentmanager;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.welcome);
        rlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(Welcome.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
