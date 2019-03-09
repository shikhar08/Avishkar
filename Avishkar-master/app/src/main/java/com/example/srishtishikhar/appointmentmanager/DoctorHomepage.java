package com.example.srishtishikhar.appointmentmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class DoctorHomepage extends AppCompatActivity {
 appointmentsDB db = new appointmentsDB(this);
 Cursor cursor;
   private EditText e1;
   private EditText e2;


 ArrayList<Appointments> lists = new ArrayList<Appointments>();
 ArrayList<Appointments> lists2 = new ArrayList<Appointments>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homepage);

        Bundle bundle=getIntent().getExtras();
        final String pid = bundle.getString("id");
        cursor = db.customQuery(pid);

          while(cursor.moveToNext()){
              String a = cursor.getString(0);
              String b = cursor.getString(1);
              String c = cursor.getString(2);
              String d = cursor.getString(3);
              String f = cursor.getString(4);
              int g = cursor.getInt(5);
              int h = cursor.getInt(6);
              Appointments n = new Appointments(a,b,c,d,f,g,h);
              lists.add(n);
          }

          homepageCustomAdapter ca = new homepageCustomAdapter(DoctorHomepage.this,lists);
          ListView lv = (ListView) findViewById(R.id.doctor_homepage_listview);
          lv.setAdapter(ca);
          lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

              @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                  db.deleteData(lists.get(position).PatId);
                  lists.remove(position);
                  homepageCustomAdapter ca = new homepageCustomAdapter(DoctorHomepage.this, lists);
                  ListView lv = (ListView) findViewById(R.id.doctor_homepage_listview);
                  lv.setAdapter(ca);

//                  String x = lists.get(position).PatId;
                  PatientDatabase d =new PatientDatabase(getApplicationContext());
                  String subject = "Cancellation of your Interview";
                  String message = "Dear Sir/Ma'am \n"+"Your appointment has been cancelled by the doctor due to some reason \n"+"Sorry for the inconvenience";
                  Intent intent = new Intent(Intent.ACTION_SEND);
                  intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                  intent.putExtra(Intent.EXTRA_TEXT, message);
                  intent.setType("message/rfc822");
                  startActivity(Intent.createChooser(intent, "Choose an email client"));
                  return true;
              }
            });

          lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                  e1 = (EditText) findViewById(R.id.M);
                  e2 = (EditText) findViewById(R.id.N);
                               cursor.moveToFirst();
                               String a = e1.getText().toString();
                               String b = e2.getText().toString();
                                Log.e("TYSON",a + " "+b);
//                               Cursor cursor = db.customQuery(lists.get(position).PatId);
                                Cursor cursor2 = db.customQuery(pid);
//                                Log.e("ZEEMAN",cursor2.getString(5)+cursor.getCount());

                                while(cursor2.moveToNext()){
                                    String A = cursor2.getString(0);
                                    String B = cursor2.getString(1);
                                    String C = cursor2.getString(2);
                                    String D = cursor2.getString(3);
                                    String E = cursor2.getString(4);
                                    int F = cursor2.getInt(5);
                                    int G = cursor2.getInt(6);
                                    int m = F+Integer.parseInt(a);
                                    int n = G+Integer.parseInt(b);
                                     if(n>60) {
                                         m = m + 1;
                                         n = n - 60;
                                     }

                                     if(m<17) {

                                         Log.e("BEEMARI", A + " " + B + " " + C + " " + D + " " + E + " " + m + " " + n);
                                         db.updateData(A, B, C, D, E, m, n);
                                     }
                                }
                               Cursor cursor1 = db.queryCommand();
                               while (cursor1.moveToNext()){
                                   String P = cursor1.getString(0);
                                   String Q = cursor1.getString(1);
                                   String R = cursor1.getString(2);
                                   String S = cursor1.getString(3);
                                   String T = cursor1.getString(4);
                                   int U = cursor1.getInt(5);
                                   int V = cursor1.getInt(6);
                                   Log.e("SHIVANI",P+" "+Q+" "+R+" "+S+" "+T+" "+U+" "+V);
                                   Appointments n = new Appointments(P,Q,R,S,T,U,V);
                                   lists.set(position,n);
                               }
                               homepageCustomAdapter ca = new homepageCustomAdapter(DoctorHomepage.this,lists);
                               ListView lv = (ListView) findViewById(R.id.doctor_homepage_listview);
                               lv.setAdapter(ca);
              }
          });



    }
}
