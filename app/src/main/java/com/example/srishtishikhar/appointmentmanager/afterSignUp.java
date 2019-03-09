package com.example.srishtishikhar.appointmentmanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class afterSignUp extends AppCompatActivity {
    static String Reg, naam;
    static String pname, page, pgender, paddress, pnum, pemail, pspecial;
    String dname, dadd, dfees, dphone, dqual, dstart, dend, dlat, dlon, did;
    DatabaseReference db;

    Doctors doctors;
    ListView list = (ListView) findViewById(R.id.doctors_list);
    ArrayList<String> dlist = new ArrayList<>();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_after_sign_up, R.id.doctors_list, dlist);
// doctors=new Doctors();
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
        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        db = fb.getReference("doctors");
        final Bundle bundle = getIntent().getExtras();
        pname = bundle.getString("name");
        pgender = bundle.getString("gender");
        page = bundle.getString("age");
        pnum = bundle.getString("phone");
        paddress = bundle.getString("add");
        pemail = bundle.getString("email");
        pspecial = bundle.getString("specialization");
//        Log.e("name", pname);
//        Log.e("gender", pgender);
//        Log.e("age", page);
//        Log.e("phone", pnum);
//        Log.e("add", paddress);
//        Log.e("email", pemail);
//        Log.e("specialization", pspecial);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sign_up);



//        db.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()) {
//
//              doctors=ds.getValue(Doctors.class);
//              dlist.add(doctors.getName().toString());
//                }
//                list.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });databaseError
        // Query s=db.orderByChild("specialization").equalTo(pspecial);
//        db.child("specialization").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot datas : dataSnapshot.getChildren()) {
//                    //did = datas.getKey();
//                    dname = datas.child("Name").getValue().toString();
//                    did = datas.child("Name").getValue().toString();
//                    dadd = datas.child("Address").getValue().toString();
//                    dfees = datas.child("Fees").getValue().toString();
//                    dphone = datas.child("Phone").getValue().toString();
//                    dqual = datas.child("Qualification").getValue().toString();
//                    dstart = datas.child("Start Time").getValue().toString();
//                    dend = datas.child("End Time").getValue().toString();
////                    dlat = datas.child("Latitude").getValue().toString();
////                    dlon = datas.child("Longitude").getValue().toString();
//
//
//
//                }
//                Bundle bundle1 = new Bundle();
//                bundle1.putString("doctor_name",dname);
//                bundle1.putString("doctor_id",did);
//
//                Intent i = new Intent(afterSignUp.this, temp.class);
//                i.putExtras(bundle1);
//                startActivity(i);
//                finish();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//
//
//    }
//}


//   cursor = db.queryCommand();
//        if (cursor.getCount() == 0)
//            return;
//
//        while (cursor.moveToNext()) {
//            String ID = cursor.getString(0);
//            String name = cursor.getString(1);
//            String specialisation = cursor.getString(2);
//            String qualification = cursor.getString(3);
//            String fee = cursor.getString(4);
//            String address = cursor.getString(5);
//            String ph = cursor.getString(6);
//            String st = cursor.getString(7);
//            String et = cursor.getString(8);
//
//            lat = cursor.getFloat(9);
//            longi = cursor.getFloat(10);

//            Log.e("NAAM",name);
//            doctors = new Doctors(name, ID, address,specialisation, Integer.parseInt(fee),qualification ,ph,Integer.parseInt(st),Integer.parseInt(et),lat,longi);
//            Log.e("WORK CHECK",doctors.name);
//            list.add(doctors);
//        }

        //   doctors = new Doctors(dname, did, dadd,pspecial, Integer.parseInt(dfees),dqual ,dphone,Integer.parseInt(dstart),Integer.parseInt(dend),dlat,dlon);
//            Log.e("WORK CHECK",doctors.name);
        // list.add(doctors);

//        final customAdapter Adapter=new customAdapter(afterSignUp.this,list,1,1);
//        final ListView listView = (ListView) findViewById(R.id.doctors_list);
//        listView.setAdapter(Adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Log.e("AGE",page);
//               Doctors a = list.get(position);
//               naam = a.name;
//               Reg = a.License_number;
//               Log.e("Angel",naam);
//               Log.e("Mankind",Reg);
//               Bundle bundle1 = new Bundle();
//               bundle1.putString("doctor_name",naam);
//               bundle1.putString("doctor_id",Reg);
//               bundle1.putString("patient_name",pname);
//               bundle1.putString("patient_num",pnum);
//               bundle1.putInt("patient_age",Integer.parseInt(page));
//
//               Intent i=new Intent(afterSignUp.this,FinalAppointmentPage.class);
//               i.putExtras(bundle1);
//               startActivity(i);
//               finish();
//           }
    }

    @Override
    protected void onStart() {

        super.onStart();

// Add listener for Firebase response on said query
        db.orderByChild("Specialisation").equalTo(pspecial).addChildEventListener(new ChildEventListener() {
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot post : dataSnapshot.getChildren()) {
//                    // Iterate through all posts with the same author
////                    Bundle bundle1 = new Bundle();
////                    bundle1.putString("doctor_name",naam);
////                    bundle1.putString("doctor_id",Reg);
////                    bundle1.putString("patient_name",pname);
////                    bundle1.putString("patient_num",pnum);
////                    bundle1.putInt("patient_age",Integer.parseInt(page));
//                    dname = post.child("Name").getValue().toString();
//
//                    Log.d("debug",dname);
//                    Intent i=new Intent(afterSignUp.this,temp.class);
//                   // i.putExtras(bundle1);
//                    startActivity(i);
//                    finish();
//
//                }
//            }

            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
             Doctors doctor = dataSnapshot.getValue(Doctors.class);
             did=dataSnapshot.getKey();
             dname= doctor.getName();
                Intent i=new Intent(afterSignUp.this,FinalAppointmentPage.class);
                Bundle bundle1 = new Bundle();
                    bundle1.putString("doctor_name",dname);
                    bundle1.putString("doctor_id",did);
                i.putExtras(bundle1);

                    startActivity(i);
                    finish();

            }



            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
