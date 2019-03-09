package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FinalAppointmentPage extends AppCompatActivity {
Cursor cursor;
    String i;
    int l;
    @Override()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_appointment_page);
        Bundle bundle3=getIntent().getExtras();
        final String doc_name=bundle3.getString("doctor_name");
        final String doc_id=bundle3.getString("doctor_id");
        final String pat_name=bundle3.getString("patient_name");
        final String pat_num=bundle3.getString("patient_num");

        final EditText a= (EditText) findViewById(R.id.editText);
        a.setText(pat_name);

        final EditText b= (EditText) findViewById(R.id.editText2);
        b.setText(pat_num);

        Button b1= (Button) findViewById(R.id.button4);
        appointmentsDB ap = new appointmentsDB(FinalAppointmentPage.this);
        cursor = ap.customQuery(doc_id);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if(cursor.getCount() == 15) {
                     Toast.makeText(getApplicationContext(),"Doctor is booked.Please book your appointment next day.",Toast.LENGTH_SHORT).show();
                     finish();
                 }
                 else
                 {
                appointmentsDB ap = new appointmentsDB(FinalAppointmentPage.this);
                if (cursor.getCount() == 0) {
                    ap.InsertCommand(doc_name, doc_id, pat_name, pat_num, 9, 0);
                    cursor = ap.customQuery(doc_id);
                    cursor.moveToLast();
                    int m = cursor.getInt(5);
                    int n = cursor.getInt(6);
                    int id = cursor.getInt(4);
                    l=id;
                    i = m+" : "+n;

                    Log.e("kk",a+"");
                }
                else {
                    cursor.moveToLast();
                    int m = cursor.getInt(5);
                    int n = cursor.getInt(6);

                    if (n == 45) {
                        ap.InsertCommand(doc_name, doc_id, pat_name, pat_num, m + 1, 0);
                        cursor = ap.customQuery(doc_id);
                        cursor.moveToLast();
                        int id = cursor.getInt(4);
                        i = (m+1)+" : "+(n+15)%60;
                        l=id;

                    }
                    else {
                        ap.InsertCommand(doc_name, doc_id, pat_name, pat_num, m, (n + 15));
                        cursor = ap.customQuery(doc_id);
                        cursor.moveToLast();
                        int id = cursor.getInt(4);
                        i = (m)+" : "+(n+15);
                        l=id;
                    }
                }
             Intent intent =  new Intent(FinalAppointmentPage.this,confirmationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Patient",pat_name);
                bundle.putString("id",l+"");
                bundle.putString("time",i);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }}
        });
        Button b2=(Button) findViewById(R.id.button5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FinalAppointmentPage.this, afterSignUp.class);
                Bundle bundle=new Bundle();
                bundle.putString("patN",pat_name);
                bundle.putString("patM",pat_num);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }
}
