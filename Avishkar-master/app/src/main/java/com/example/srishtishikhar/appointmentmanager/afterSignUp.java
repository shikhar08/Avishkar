package com.example.srishtishikhar.appointmentmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class afterSignUp extends AppCompatActivity {
    static String Reg,naam;
    static String patN,patM,patA;

    DatabaseHelper db = new DatabaseHelper(this);
    Cursor cursor;
    Doctors doctors;
    ArrayList<Doctors> list = new ArrayList<Doctors>();

    float lat;
    float longi;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Insert dummy data" menu option
//            case R.id.file:
//                break;
//        }
//        return true;
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Bundle bundle=getIntent().getExtras();
        patN=bundle.getString("patN");
        patA=bundle.getString("patA");
        patM=bundle.getString("patM");
        Log.e("name",patN);
        Log.e("age",patA);
        Log.e("number",patM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sign_up);

        cursor = db.queryCommand();
        if (cursor.getCount() == 0)
            return;

        while (cursor.moveToNext()) {
            String ID = cursor.getString(0);
            String name = cursor.getString(1);
            String specialisation = cursor.getString(2);
            String qualification = cursor.getString(3);
            String fee = cursor.getString(4);
            String address = cursor.getString(5);
            String ph = cursor.getString(6);
            String sh = cursor.getString(7);
            String sm = cursor.getString(8);
            String eh = cursor.getString(9);
            String em = cursor.getString(10);
            lat = cursor.getFloat(11);
            longi = cursor.getFloat(12);

            Log.e("NAAM",name);
            doctors = new Doctors(name, ID, address,specialisation, Integer.parseInt(fee),qualification ,ph,Integer.parseInt(sh),Integer.parseInt(sm),Integer.parseInt(eh),Integer.parseInt(em),lat,longi);
            Log.e("WORK CHECK",doctors.name);
            list.add(doctors);
        }

        final customAdapter Adapter=new customAdapter(afterSignUp.this,list,lat,longi);
        final ListView listView = (ListView) findViewById(R.id.doctors_list);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Log.e("AGE",patA);
               Doctors a = list.get(position);
               naam = a.name;
               Reg = a.License_number;
               Log.e("Angel",naam);
               Log.e("Mankind",Reg);
               Bundle bundle1 = new Bundle();
               bundle1.putString("doctor_name",naam);
               bundle1.putString("doctor_id",Reg);
               bundle1.putString("patient_name",patN);
               bundle1.putString("patient_num",patM);
               bundle1.putInt("patient_age",Integer.parseInt(patA));

               Intent i=new Intent(afterSignUp.this,FinalAppointmentPage.class);
               i.putExtras(bundle1);
               startActivity(i);
               finish();
           }
         });
    }
}

