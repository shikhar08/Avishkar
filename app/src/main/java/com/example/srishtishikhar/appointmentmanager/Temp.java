package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import java.lang.String;

public class Temp extends AppCompatActivity {

    TextView t1, t2;
    DatabaseReference db, db2;
    Doctors doc;
    //ListView list = (ListView) findViewById(R.id.doctors_list);
//    ArrayList<String> dlist = new ArrayList<>();
//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_temp, R.id.doctors_list, dlist);
//    String did, dname, dfees, dadd, dphone, dstart, dqual, dend;
    String[] doclist;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        t1 = (TextView) findViewById(R.id.textView);
       // t2 = (TextView) findViewById(R.id.textView3);
        final Bundle bundle = getIntent().getExtras();
        final String pspecial = bundle.getString("specialization");
        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        final FirebaseDatabase fb2 = FirebaseDatabase.getInstance();
        db = fb.getReference("Specialisations");
        db2 = fb2.getReference("doctors");
//        db.child(pspecial).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int count = (int) dataSnapshot.getChildrenCount(); // retrieve number of childrens under Donald Trump
//                doclist = new String[count];
//                int index = 0;
//                for (DataSnapshot datas : dataSnapshot.getChildren()) {
//                    doclist[index] = datas.getKey();
//                    index++;
//                    }
//              //  t1.setText(doclist[1]);
//                    }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
       // String val=doclist[1];
        db2.child("11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                int count = (int) dataSnapshot.getChildrenCount(); // retrieve number of childrens under Donald Trump
////                String[] doclist2 = new String[count];
////                int index = 0;
////                for (DataSnapshot datas : dataSnapshot.getChildren()) {
////                    doclist2[index] = datas.getValue(String.class);
////                   index++;
////                    }

                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    doc=datas.getValue(Doctors.class);


                }
          String s= doc.getPhone();
                t1.setText(s);
                    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
             // for (index = 0; index < count; index++) {
//                    db2.child(doclist[1]).addChildEventListener(new ChildEventListener(){
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
//                                did = datas.getKey();
//                                dname = datas.child("Name").getValue().toString();
//                                did = datas.child("Name").getValue().toString();
//                                dadd = datas.child("Address").getValue().toString();
//                                dfees = datas.child("Fees").getValue().toString();
//                                dphone = datas.child("Phone").getValue().toString();
//                                dqual = datas.child("Qualification").getValue().toString();
//                                dstart = datas.child("Start Time").getValue().toString();
//                                dend = datas.child("End Time").getValue().toString();
//                            }
//                          //  t1.setText(dname);
//                        }
//
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//
//                        }
//                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                        }
//
//
//                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                        }

//
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//
////                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
//                                did = datas.getKey();
//                                dname = datas.child("Name").getValue().toString();
//                                did = datas.child("Name").getValue().toString();
//                                dadd = datas.child("Address").getValue().toString();
//                                dfees = datas.child("Fees").getValue().toString();
//                                dphone = datas.child("Phone").getValue().toString();
//                                dqual = datas.child("Qualification").getValue().toString();
//                                dstart = datas.child("Start Time").getValue().toString();
//                                dend = datas.child("End Time").getValue().toString();
////                               // doctors = new Doctors(dname, did, dadd, pspecial, Integer.parseInt(dfees), dqual, dphone, Integer.parseInt(dstart), Integer.parseInt(dend));
//////            Log.e("WORK CHECK",doctors.name);
////                                //list.add(doctors);
////                            }
//                            t1.setText(doclist[1]);
//                        }



//                    });




    }
}


